package com.example.evoo.data

import com.example.evoo.R // R-Klasse muss aus dem Projekt-Root kommen
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import android.content.Context // Context für String-Ressourcen
import android.util.Log
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

// Helper-Objekt für den Ktor-Client
object KtorClient {
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Wichtig, da API-Antworten viele Felder haben können
                prettyPrint = true
                isLenient = true // Auch wichtig für robuste JSON-Verarbeitung
            })
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.i("KtorClient", message) // Ktor-Ausgaben in Logcat schreiben
                }
            }
            level = LogLevel.ALL // Alle Details protokollieren
        }
    }
}

// Data Classes für die Ticketmaster API-Antwort
// Dies sind die gleichen Data Classes wie zuvor, nur das Paket ist jetzt korrekt.
@Serializable
data class TicketmasterSearchResponse(
    val _embedded: EmbeddedEvents? = null,
    val page: PageInfo
)

@Serializable
data class EmbeddedEvents(
    val events: List<TicketmasterEvent> = emptyList()
)

@Serializable
data class TicketmasterEvent(
    val id: String,
    val name: String,
    val type: String, // event, attraction, venue
    val url: String,
    val locale: String,
    val images: List<TicketmasterImage>? = null,
    val dates: TicketmasterDates? = null,
    val _embedded: TicketmasterEventEmbedded? = null // Für Venue, Attractions etc.
)

@Serializable
data class TicketmasterImage(
    val ratio: String? = null,
    val url: String,
    val width: Int,
    val height: Int,
    val fallback: Boolean
)

@Serializable
data class TicketmasterDates(
    val start: TicketmasterStartDates? = null
)

@Serializable
data class TicketmasterStartDates(
    val localDate: String? = null, // z.B. "2025-08-01"
    val localTime: String? = null, // z.B. "19:00:00"
    val dateTime: String? = null // z.B. "2025-08-01T19:00:00Z"
)

@Serializable
data class TicketmasterEventEmbedded(
    val venues: List<TicketmasterVenue>? = null,
    val attractions: List<TicketmasterAttraction>? = null
)

@Serializable
data class TicketmasterVenue(
    val id: String,
    val name: String? = null,
    val address: TicketmasterAddress? = null,
    val city: TicketmasterCity? = null,
    val state: TicketmasterState? = null,
    val country: TicketmasterCountry? = null,
    val location: TicketmasterLocation? = null
)

@Serializable
data class TicketmasterAttraction(
    val id: String,
    val name: String,
    val url: String,
    val images: List<TicketmasterImage>? = null
)

@Serializable
data class TicketmasterAddress(
    val line1: String? = null // z.B. "Mercedes Platz 1"
)

@Serializable
data class TicketmasterCity(
    val name: String? = null // z.B. "Berlin"
)

@Serializable
data class TicketmasterState(
    val name: String? = null, // z.B. "Berlin" (in DE, kann auch leer sein)
    val stateCode: String? = null // z.B. "BE"
)

@Serializable
data class TicketmasterCountry(
    val name: String? = null, // z.B. "Germany"
    val countryCode: String? = null // z.B. "DE"
)

@Serializable
data class TicketmasterLocation(
    val longitude: String? = null,
    val latitude: String? = null
)

@Serializable
data class PageInfo(
    val size: Int,
    val totalElements: Int,
    val totalPages: Int,
    val number: Int
)

class TicketmasterApiService(private val context: Context) {
    private val BASE_URL = "https://app.ticketmaster.com/discovery/v2/"

    private val API_KEY: String by lazy {
        context.getString(R.string.ticketmaster_api_key)
    }

    suspend fun searchEvents(
        query: String? = null,
        city: String? = null,
        countryCode: String? = null, // NEU: Optionaler countryCode Parameter
        page: Int = 0,
        pageSize: Int = 20
    ): TicketmasterSearchResponse {
        return KtorClient.httpClient.get("${BASE_URL}events.json") {
            parameter("apikey", API_KEY)
            query?.let { parameter("keyword", it) }
            city?.let { parameter("city", it) }
            countryCode?.let { parameter("countryCode", it) } // NEU: countryCode zur Anfrage hinzufügen
            parameter("page", page)
            parameter("size", pageSize)
            // Optional: Land, z.B. für Deutschland: parameter("countryCode", "DE")
        }.body()
    }


}
private val BASE_URL = "https://app.ticketmaster.com/discovery/v2/"

private val API_KEY: String = "X0B57u3BuSKfCFLvWjCPRoFMJtA5xiVQ"

suspend fun loadEvents(
    city: String, // Hinzufügen einer Standard-Stadt
    countryCode: String = "DE"
): List<TicketmasterEvent> {
    return try {
        withContext(Dispatchers.IO){
            val response: TicketmasterSearchResponse = KtorClient.httpClient.get("${BASE_URL}events.json"){
                parameter("apikey", API_KEY)
                parameter("city", city) // Default-Stadt-Parameter hinzufügen
                parameter("countryCode", countryCode) // Default-Länder-Code hinzufügen
                println("suspend fun loadEvents in TicketmasterApiService used")
            }.body()
            // Hier extrahieren wir die Liste aus dem verschachtelten Objekt
            response._embedded?.events ?: emptyList()
        }
    } catch (e: Exception){
        println("Fehler beim laden von Events: ${e.message}")
        e.printStackTrace() // Wichtig, um den genauen Fehler im Logcat zu sehen
        emptyList()
    }
}

suspend fun getEventById(eventId: String): TicketmasterEvent? {
    return try {
        withContext(Dispatchers.IO) {
            val response: TicketmasterEvent = KtorClient.httpClient.get("${BASE_URL}events/$eventId.json") {
                parameter("apikey", API_KEY)
            }.body()
            response
        }
    } catch (e: Exception) {
        Log.e("TicketmasterApiService", "Error loading event with ID $eventId: ${e.message}")
        null
    }
}


fun eventsDataFlow(city: String): Flow<List<TicketmasterEvent>> = flow {
    emit(loadEvents(city))
}

fun eventByIdFlow(eventId: String): Flow<TicketmasterEvent?> = flow{
    emit(getEventById(eventId))
}

interface EventsRepository{
    fun getEventsDataFlow(city: String): Flow<List<TicketmasterEvent>>
}

interface EventByIdData{
    fun getEventByIdFlow(eventId: String): Flow<TicketmasterEvent?>
}

class EventsRepositoryImplFlow: EventsRepository{
    override fun getEventsDataFlow(city: String): Flow<List<TicketmasterEvent>> = eventsDataFlow(city)
}

class EventByIdImplFlow: EventByIdData{
    override fun getEventByIdFlow(eventId: String): Flow<TicketmasterEvent?> = eventByIdFlow(eventId)
}
