package com.example.evoo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evoo.AccentColor
import com.example.evoo.BottomDarkBlue
import com.example.evoo.R
import com.example.evoo.TopLightBlue
import com.example.evoo.ui.menu.MenuBar

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

@Composable
fun EventContent(navController: NavController) {

    // Liste aller Festival-Bilder
    val festivalImages = listOf(
        R.drawable.festival1, R.drawable.festival2, R.drawable.festival3,
        R.drawable.festival4, R.drawable.festival5, R.drawable.festival6,
        R.drawable.festival7, R.drawable.festival8, R.drawable.festival9,
        R.drawable.festival10, R.drawable.festival11, R.drawable.festival12,
        R.drawable.festival1, R.drawable.festival2, R.drawable.festival3,
        R.drawable.festival4, R.drawable.festival5, R.drawable.festival6,
        R.drawable.festival7, R.drawable.festival8, R.drawable.festival9,
        R.drawable.festival10, R.drawable.festival11, R.drawable.festival12,
    )

    // State, um das ausgewählte Bild zu speichern
    var selectedImage by remember { mutableStateOf<Int?>(null) }


    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        columns = GridCells.Fixed(2)
    ){
        items (festivalImages.size){ index ->
            val imageIndex = festivalImages[index]
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .aspectRatio(1f)
                    .clickable{
                        selectedImage = imageIndex
                        //navController.navigate("ContentDetailScreen")
                    }
            ){
                Image(
                    painter = painterResource(id = imageIndex),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))


                )
            }


        }
    }

    // Overlay für vergrößertes Bild, wenn selectedImage nicht null ist
    selectedImage?.let { imageIndex ->
        Surface(
            color = Color.Black.copy(alpha = 0.7f), // Dunkler Hintergrund
            modifier = Modifier.fillMaxSize(),
            onClick = { selectedImage = null } // Klick außerhalb schließt das Overlay
        ) {
            Box (
                modifier = Modifier.fillMaxSize()
            ){
                Image(
                    painter = painterResource(id = imageIndex),
                    contentDescription = "Vergrößertes Bild",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.5f)
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

        }
    }

}