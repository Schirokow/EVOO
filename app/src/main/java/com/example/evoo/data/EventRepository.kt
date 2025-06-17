package com.example.evoo.data

import com.example.evoo.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class FestivalData(
    val id: Int,
    val imageId: Int,
    val title: String,
    val description: String,
    val datum: String,
    val location: String
)



// Liste aller Events
private var festivalData = mutableListOf(
        FestivalData(
            id = 1,
            imageId = R.drawable.festival1,
            title = "Summer Festival",
            description = "Techno Festival",
            datum = "20 & 21 Juni 2025",
            location = "Am Strand"
        ),
        FestivalData(
            id = 2,
            imageId = R.drawable.festival2,
            title = "Heaven & Hill Neukirchen",
            description = "Disco-Fest",
            datum = "20 & 21 Juni 2025",
            location = "Neukirchen-Vluyn"
        ),
        FestivalData(
            id = 3,
            imageId = R.drawable.festival3,
            title = "LOVEMUSIC Festival Magdeburg",
            description = "Party bis zum Umfallen!!!",
            datum = "20.-22.06 2025",
            location = "Elbauenpark Magdeburg"
        ),
        FestivalData(
            id = 4,
            imageId = R.drawable.festival4,
            title = "CAPTAIN JACK Weiden",
            description = "Festival in Weiden",
            datum = "04.07.2025",
            location = "Festplatz Weiden"
        ),
        FestivalData(
            id = 5,
            imageId = R.drawable.festival5,
            title = "GLÜCKSGEFÜHLE Festival",
            description = "Euphoria Stage",
            datum = "August 2025",
            location = "Discoland"
        ),
        FestivalData(
            id = 6,
            imageId = R.drawable.festival6,
            title = "SUMMER FOOD ROCK",
            description = "Rock Festival",
            datum = "28 Juni 2025",
            location = "OPEN AIR"
        ),
        FestivalData(
            id = 7,
            imageId = R.drawable.festival7,
            title = "HAVEL BEATS Festival",
            description = "Techno Festival",
            datum = "15 & 16 August 2025",
            location = "Stadion"
        ),
        FestivalData(
            id = 8,
            imageId = R.drawable.festival8,
            title = "FESTIVAL HOCKENHEIMRING",
            description = "Techno Festival",
            datum = "11-14 September 2025",
            location = "Hockenheimring"
        ),
        FestivalData(
            id = 9,
            imageId = R.drawable.festival9,
            title = "DAS HR-FESTIVAL 2025",
            description = "Embrace Festival",
            datum = "25 & 26 Juni 2025",
            location = "Berlin"
        ),
        FestivalData(
            id = 10,
            imageId = R.drawable.festival10,
            title = "TAUBERTAL Festival 2025",
            description = "Festival",
            datum = "07.Bis 10.August 2025",
            location = "Rothenburg Ob Der Tauber"
        ),
        FestivalData(
            id = 11,
            imageId = R.drawable.festival11,
            title = "BLANKENFELDE Festival Juli 2025",
            description = "Rock Festival",
            datum = "25 + 26 Juli 2025",
            location = "Blankenfelde"
        ),
        FestivalData(
            id = 12,
            imageId = R.drawable.festival12,
            title = "Latin Airport Festival",
            description = "Latino Festival",
            datum = "05.Juli 2025",
            location = "Airport Nürnberg"
        ),
        FestivalData(
            id = 13,
            imageId = R.drawable.festival1,
            title = "Summer Festival",
            description = "Techno Festival",
            datum = "20 & 21 Juni 2025",
            location = "Am Strand"
        ),
        FestivalData(
            id = 14,
            imageId = R.drawable.festival2,
            title = "Heaven & Hill Neukirchen",
            description = "Disco-Fest",
            datum = "20 & 21 Juni 2025",
            location = "Neukirchen-Vluyn"
        ),
        FestivalData(
            id = 15,
            imageId = R.drawable.festival3,
            title = "LOVEMUSIC Festival Magdeburg",
            description = "Party bis zum Umfallen!!!",
            datum = "20.-22.06 2025",
            location = "Elbauenpark Magdeburg"
        ),
        FestivalData(
            id = 16,
            imageId = R.drawable.festival4,
            title = "CAPTAIN JACK Weiden",
            description = "Festival in Weiden",
            datum = "04.07.2025",
            location = "Festplatz Weiden"
        ),
        FestivalData(
            id = 17,
            imageId = R.drawable.festival5,
            title = "GLÜCKSGEFÜHLE Festival",
            description = "Euphoria Stage",
            datum = "August 2025",
            location = "Discoland"
        ),
        FestivalData(
            id = 18,
            imageId = R.drawable.festival6,
            title = "SUMMER FOOD ROCK",
            description = "Rock Festival",
            datum = "28 Juni 2025",
            location = "OPEN AIR"
        ),
        FestivalData(
            id =19,
            imageId = R.drawable.festival7,
            title = "HAVEL BEATS Festival",
            description = "Techno Festival",
            datum = "15 & 16 August 2025",
            location = "Stadion"
        ),
        FestivalData(
            id = 20,
            imageId = R.drawable.festival8,
            title = "FESTIVAL HOCKENHEIMRING",
            description = "Techno Festival",
            datum = "11-14 September 2025",
            location = "Hockenheimring"
        ),
        FestivalData(
            id = 21,
            imageId = R.drawable.festival9,
            title = "DAS HR-FESTIVAL 2025",
            description = "Embrace Festival",
            datum = "25 & 26 Juni 2025",
            location = "Berlin"
        ),
        FestivalData(
            id = 22,
            imageId = R.drawable.festival10,
            title = "TAUBERTAL Festival 2025",
            description = "Festival",
            datum = "07.Bis 10.August 2025",
            location = "Rothenburg Ob Der Tauber"
        ),
        FestivalData(
            id = 23,
            imageId = R.drawable.festival11,
            title = "BLANKENFELDE Festival Juli 2025",
            description = "Rock Festival",
            datum = "25 + 26 Juli 2025",
            location = "Blankenfelde"
        ),
        FestivalData(
            id = 24,
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

interface FestivalRepository {
    fun getFestivalsFlow(): Flow<List<FestivalData>>
    fun getFestivalByIdFlow(id: Int): Flow<FestivalData?>
}

class FestivalRepositoryImpl : FestivalRepository {

// Hier käme normalerweise API/Datenbank-Zugriff
override fun getFestivalsFlow(): Flow<List<FestivalData>> = festivalDataFlow()

override fun getFestivalByIdFlow(id: Int): Flow<FestivalData?> = flow {
        festivalDataFlow().collect { festivals ->
            val festivalId = festivals.find { it.id == id }
            emit(festivalId)
        }
    }


}