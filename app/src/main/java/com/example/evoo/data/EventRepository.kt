package com.example.evoo.data

import com.example.evoo.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.evoo.data.dao.FavoriteDao
import com.example.evoo.data.dao.FestivalDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


@Entity(tableName = "festivals")
data class FestivalData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Eindeutige ID, automatisch generiert
    val imageId: Int,
    val title: String,
    val description: String,
    val datum: String,
    val location: String
)
// Erklärung:
//@Entity(tableName = "festivals"): Markiert die Klasse als Datenbanktabelle mit dem Namen "festivals".
//@PrimaryKey(autoGenerate = true): Definiert id als Primärschlüssel, der automatisch inkrementiert wird.
//Die restlichen Felder (imageId, title, etc.) werden als Spalten in der Tabelle gespeichert.


@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val festivalId: Int, // Verweist auf die ID eines Festivals in der festivals-Tabelle
    val imageId: Int,
    val title: String,
    val description: String,
    val datum: String,
    val location: String
)
//Erklärung:
//Die favorites-Tabelle speichert nur die festivalId, die auf die id-Spalte der festivals-Tabelle verweist.
//@PrimaryKey stellt sicher, dass jede festivalId eindeutig ist, um Duplikate zu vermeiden.


interface FestivalRepository {
    suspend fun insertFestival(festival: List<FestivalData>)
    fun getFestivals(): Flow<List<FestivalData>>
    suspend fun getFestivalById(id: String): FestivalData?

    suspend fun deleteAllFestivals()
}

class FestivalRepositoryImpl(private val dao: FestivalDao) : FestivalRepository {
    override suspend fun insertFestival(festival: List<FestivalData>) {
        dao.insert(festival)
    }

    override fun getFestivals(): Flow<List<FestivalData>> {
        return dao.getAllFestivals()
    }

    override suspend fun getFestivalById(id: String): FestivalData? {
        return dao.getFestivalById(id)
    }

    override suspend fun deleteAllFestivals() {
        dao.deleteAllFestivals()
    }
}
//Erklärung:
//Das Repository verwendet das FestivalDao, um Datenbankoperationen durchzuführen.
//Flow<List<FestivalData>> ermöglicht reaktive Updates, wenn sich die Daten ändern.

interface FavoriteRepository {
    suspend fun addFavorite(festival: FestivalData)
    suspend fun removeFavorite(festivalId: Int)
    fun getFavoriteFestivals(): Flow<List<Favorite>>
    suspend fun isFavorite(festivalId: Int): Boolean
    suspend fun deleteAllFavorites()
}

class FavoriteRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {
    override suspend fun addFavorite(festival: FestivalData) {
        favoriteDao.insertFavorite(
            Favorite(
                festivalId = festival.id,
                imageId = festival.imageId,
                title = festival.title,
                description = festival.description,
                datum = festival.datum,
                location = festival.location
            )
        )
    }

    override suspend fun removeFavorite(festivalId: Int) {
        favoriteDao.deleteFavorite(festivalId)
    }

    override fun getFavoriteFestivals(): Flow<List<Favorite>> {
        return favoriteDao.getAllFavorites()
    }

    override suspend fun isFavorite(festivalId: Int): Boolean {
        return favoriteDao.isFavorite(festivalId)
    }

    override suspend fun deleteAllFavorites() {
        favoriteDao.deleteAllFavorites()
    }
}
//Erklärung:
//addFavorite nimmt ein FestivalData-Objekt und konvertiert es in ein Favorite-Objekt.
//getFavoriteFestivals gibt direkt Flow<List<Favorite>> zurück.

// Liste aller Events
private var festivalData = mutableListOf(
        FestivalData(
            imageId = R.drawable.festival1,
            title = "Summer Festival",
            description = "Techno Festival",
            datum = "20 & 21 Juni 2025",
            location = "Am Strand"
        ),
        FestivalData(
            imageId = R.drawable.festival2,
            title = "Heaven & Hill Neukirchen",
            description = "Disco-Fest",
            datum = "20 & 21 Juni 2025",
            location = "Neukirchen-Vluyn"
        ),
        FestivalData(
            imageId = R.drawable.festival3,
            title = "LOVEMUSIC Festival Magdeburg",
            description = "Party bis zum Umfallen!!!",
            datum = "20.-22.06 2025",
            location = "Elbauenpark Magdeburg"
        ),
        FestivalData(
            imageId = R.drawable.festival4,
            title = "CAPTAIN JACK Weiden",
            description = "Festival in Weiden",
            datum = "04.07.2025",
            location = "Festplatz Weiden"
        ),
        FestivalData(
            imageId = R.drawable.festival5,
            title = "GLÜCKSGEFÜHLE Festival",
            description = "Euphoria Stage",
            datum = "August 2025",
            location = "Discoland"
        ),
        FestivalData(
            imageId = R.drawable.festival6,
            title = "SUMMER FOOD ROCK",
            description = "Rock Festival",
            datum = "28 Juni 2025",
            location = "OPEN AIR"
        ),
        FestivalData(
            imageId = R.drawable.festival7,
            title = "HAVEL BEATS Festival",
            description = "Techno Festival",
            datum = "15 & 16 August 2025",
            location = "Stadion"
        ),
        FestivalData(
            imageId = R.drawable.festival8,
            title = "FESTIVAL HOCKENHEIMRING",
            description = "Techno Festival",
            datum = "11-14 September 2025",
            location = "Hockenheimring"
        ),
        FestivalData(
            imageId = R.drawable.festival9,
            title = "DAS HR-FESTIVAL 2025",
            description = "Embrace Festival",
            datum = "25 & 26 Juni 2025",
            location = "Berlin"
        ),
        FestivalData(
            imageId = R.drawable.festival10,
            title = "TAUBERTAL Festival 2025",
            description = "Festival",
            datum = "07.Bis 10.August 2025",
            location = "Rothenburg Ob Der Tauber"
        ),
        FestivalData(
            imageId = R.drawable.festival11,
            title = "BLANKENFELDE Festival Juli 2025",
            description = "Rock Festival",
            datum = "25 + 26 Juli 2025",
            location = "Blankenfelde"
        ),
        FestivalData(
            imageId = R.drawable.festival12,
            title = "Latin Airport Festival",
            description = "Latino Festival",
            datum = "05.Juli 2025",
            location = "Airport Nürnberg"
        ),
        FestivalData(
            imageId = R.drawable.festival1,
            title = "Summer Festival",
            description = "Techno Festival",
            datum = "20 & 21 Juni 2025",
            location = "Am Strand"
        ),
        FestivalData(
            imageId = R.drawable.festival2,
            title = "Heaven & Hill Neukirchen",
            description = "Disco-Fest",
            datum = "20 & 21 Juni 2025",
            location = "Neukirchen-Vluyn"
        ),
        FestivalData(
            imageId = R.drawable.festival3,
            title = "LOVEMUSIC Festival Magdeburg",
            description = "Party bis zum Umfallen!!!",
            datum = "20.-22.06 2025",
            location = "Elbauenpark Magdeburg"
        ),
        FestivalData(
            imageId = R.drawable.festival4,
            title = "CAPTAIN JACK Weiden",
            description = "Festival in Weiden",
            datum = "04.07.2025",
            location = "Festplatz Weiden"
        ),
        FestivalData(
            imageId = R.drawable.festival5,
            title = "GLÜCKSGEFÜHLE Festival",
            description = "Euphoria Stage",
            datum = "August 2025",
            location = "Discoland"
        ),
        FestivalData(
            imageId = R.drawable.festival6,
            title = "SUMMER FOOD ROCK",
            description = "Rock Festival",
            datum = "28 Juni 2025",
            location = "OPEN AIR"
        ),
        FestivalData(
            imageId = R.drawable.festival7,
            title = "HAVEL BEATS Festival",
            description = "Techno Festival",
            datum = "15 & 16 August 2025",
            location = "Stadion"
        ),
        FestivalData(
            imageId = R.drawable.festival8,
            title = "FESTIVAL HOCKENHEIMRING",
            description = "Techno Festival",
            datum = "11-14 September 2025",
            location = "Hockenheimring"
        ),
        FestivalData(
            imageId = R.drawable.festival9,
            title = "DAS HR-FESTIVAL 2025",
            description = "Embrace Festival",
            datum = "25 & 26 Juni 2025",
            location = "Berlin"
        ),
        FestivalData(
            imageId = R.drawable.festival10,
            title = "TAUBERTAL Festival 2025",
            description = "Festival",
            datum = "07.Bis 10.August 2025",
            location = "Rothenburg Ob Der Tauber"
        ),
        FestivalData(
            imageId = R.drawable.festival11,
            title = "BLANKENFELDE Festival Juli 2025",
            description = "Rock Festival",
            datum = "25 + 26 Juli 2025",
            location = "Blankenfelde"
        ),
        FestivalData(
            imageId = R.drawable.festival12,
            title = "Latin Airport Festival",
            description = "Latino Festival",
            datum = "05.Juli 2025",
            location = "Airport Nürnberg"
        ),
    )

fun festivalDataFlow(): Flow<List<FestivalData>> = flow {
    emit(festivalData)
}

interface FestivalRepositoryFlow{
    fun getFestivalsFlow(): Flow<List<FestivalData>>
}

class FestivalRepositoryImplFlow: FestivalRepositoryFlow{
    override fun getFestivalsFlow(): Flow<List<FestivalData>> = festivalDataFlow()
}

//interface FestivalRepository {
//    fun getFestivalsFlow(): Flow<List<FestivalData>>
//    fun getFestivalByIdFlow(id: Int): Flow<FestivalData?>
//}
//
//class FestivalRepositoryImpl : FestivalRepository {
//
//// Hier käme normalerweise API/Datenbank-Zugriff
//override fun getFestivalsFlow(): Flow<List<FestivalData>> = festivalDataFlow()
//
//override fun getFestivalByIdFlow(id: Int): Flow<FestivalData?> = flow {
//        festivalDataFlow().collect { festivals ->
//            val festivalId = festivals.find { it.id == id }
//            emit(festivalId)
//        }
//    }
//}

