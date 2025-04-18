package com.example.evoo.ui.screens

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evoo.AccentColor
import com.example.evoo.BottomDarkBlue
import com.example.evoo.R
import com.example.evoo.TopLightBlue
import com.example.evoo.ui.menu.MenuBar
import com.example.evoo.ui.screens.FestivalRepository.festivalData


// Startseite
@Composable
fun HomeScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AccentColor)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues()) // Eine Function um den Content unter der Status Bar anzuzeigen.
                .background(brush = Brush.verticalGradient(colors = listOf(
                    TopLightBlue,
                    BottomDarkBlue
                )))
        ){
            // Funktion für die Vorschau.
            EventContent(navController)

            Box (
                modifier = Modifier
                    .padding(top = 6.dp)
            )
            {
                //LogoImage()   // Function um Logo auf dem Screen darzustellen.
            }
            // Menu Bar
            MenuBar(navController)
        }
    }
}

data class FestivalData(
    val imageId: Int,
    val title: String,
    val description: String,
    val datum: String,
    val location: String
)

object FestivalRepository{

    // Liste aller Events
    val festivalData = listOf(
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


@Composable
fun EventContent(navController: NavController) {

    // State, um das ausgewählte Bild zu speichern
    var selectedImage by remember { mutableStateOf<Int?>(null) }


    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        columns = GridCells.Fixed(2)
    ){
        items (festivalData.size){ index ->
            val festivalData = festivalData[index]
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .aspectRatio(1f)
            ){
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable{
                            selectedImage = index
                        },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = festivalData.imageId),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

            }
        }
    }

    val animateScale by animateFloatAsState(
        targetValue = if (selectedImage != null) 1f else 0.5f,
        animationSpec = tween(durationMillis = 400)
    )

    // Overlay für vergrößertes Bild, wenn selectedImage nicht null ist
    selectedImage?.let { index ->
        Log.d("LazyVerticalGrid", "let Block -> Value in selectedImage: $selectedImage, Value in index: $index")
        val festivalData = festivalData[index] // Datenobjekt via Index
        Log.d("LazyVerticalGrid", "let Block -> Value in festivalData: $festivalData")
        Surface(
            color = BackgroundColor.copy(alpha = 0.9f), // Farbe von Hintergrund
            modifier = Modifier.fillMaxSize(),
            onClick = { /*selectedImage = null */} // Klick außerhalb schließt das Overlay
        ) {
            Box (
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    Text(
                        text = festivalData.title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Card (
                        modifier = Modifier
                            .graphicsLayer(scaleX = animateScale, scaleY = animateScale)
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.5f)
                            .clickable{navController.navigate("ContentDetailScreen/$index")},
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(12.dp)
                    ){
                        Image(
                            painter = painterResource(id = festivalData.imageId),
                            contentDescription = "Vergrößertes Bild",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }


                }

                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(24.dp)
                        .size(34.dp)
                        .clickable{ selectedImage = null}
                    )

                Icon(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.White,
                    modifier = Modifier
                        .align(alignment = Alignment.TopEnd)
                        .padding(24.dp)
                        .size(34.dp)
                        .clickable{ }
                )
            }

        }
    }

}

val BackgroundColor = Color(0xFF20587B)


