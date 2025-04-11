package com.example.evoo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.evoo.TopLightBlue
import com.example.evoo.ui.menu.MenuBar
import com.example.evoo.ui.screens.FestivalRepository.festivalImages

@Composable
fun ContentDetailScreen(navController: NavController, index: Int){

    // Holt die FestivalData aus der gemeinsamen Liste
    val festivalData = festivalImages[index]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AccentColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues())
                .background(brush = Brush.verticalGradient(colors = listOf(
                    TopLightBlue,
                    BottomDarkBlue
                )))
        ) {

            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Zurück",
                tint = Color.White,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .padding(24.dp)
                    .size(34.dp)
                    .clickable { navController.popBackStack() }
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Titel
                Text(
                    text = festivalData.title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White
                )

                // Bild
                Image(
                    painter = painterResource(id = festivalData.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        //.padding(16.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                // Beschreibung
                Text(
                    text = "Beschreibung: ${festivalData.description}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )

                // Datum
                Text(
                    text = "Datum: ${festivalData.datum}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )

                // Location
                Text(
                    text = "Veranstaltungsort: ${festivalData.location}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )

                Button(
                    onClick = {}
                ) {
                    Text(text = "Auf der Karte zeigen")
                }


            }

            // Menu Bar
            MenuBar(navController)
        }
    }
}