package com.example.evoo.eventRepository

import com.example.evoo.R

data class FestivalData(
    val imageId: Int,
    val title: String,
    val description: String,
    val datum: String,
    val location: String
)

object EventRepository{

    // Liste aller Events
    var festivalData = mutableListOf(
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
}