package com.example.evoo.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.evoo.AccentColor
import com.example.evoo.BottomDarkBlue
import com.example.evoo.TopLightBlue
import com.example.evoo.presentation.viewmodels.ContentDetailViewModel
import com.example.evoo.presentation.viewmodels.HomeViewModel

import com.example.evoo.ui.components.buttons.ClickButton
import com.example.evoo.ui.menu.AnyeBottomBar

private const val TAG = "ContentDetailScreen"


@Composable
fun ContentDetailScreen(navController: NavController, index: Int,viewModel: ContentDetailViewModel = viewModel()){
    Log.d(TAG, "Screen initialized with index: $index")

    // FestivalData beim ersten Erscheinen laden
    LaunchedEffect(Unit) {
        viewModel.loadFestivalData()
    }

    // Holen der Festival-Daten aus dem StateFlow
    val festivalList by viewModel.festivalData.collectAsState()

    // Sicherstellen, dass der Index gültig ist
    if (index < 0 || index >= festivalList.size) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text("Ungültiger Festival-Index", color = Color.White, fontSize = 24.sp)
        }
        return
    }

    // Das spezifische Festival basierend auf dem Index holen
    val festivalData = festivalList[index]



    // Holt die FestivalData aus der gemeinsamen Liste
//    val festivalData = festivalData[index].also {
//        Log.i(TAG, "Loaded festival data: ${it.title.take(15)}...")
//    }


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
                    .clickable {
                        Log.d(TAG, "Navigation: Returning to previous screen")
                        navController.popBackStack()
                    }
            )

            Icon(
                imageVector = Icons.Rounded.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.White,
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .padding(24.dp)
                    .size(34.dp)
                    .clickable{
                        Log.i(TAG, "Favorite clicked for: ${festivalData.title.take(15)}...")
                    }
            )

            ClickButton(
                text = "Auf der Karte",
                onClick = {
                    Log.d(TAG, "Navigating to location screen")
                    navController.navigate("LocationScreen")
                          },
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 24.dp, start = 120.dp, end = 120.dp)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Log.d(TAG, "Rendering content for: ${festivalData.title.take(15)}...")
                // Titel
                Text(
                    text = festivalData.title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                // Bild
                Card (
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.5f),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(12.dp)
                ){
                    Image(
                        painter = painterResource(id = festivalData.imageId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }


                // Beschreibung
                Text(
                    text = "Beschreibung: ${festivalData.description}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp),
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )

                // Datum
                Text(
                    text = "Datum: ${festivalData.datum}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp),
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )

                // Location
                Text(
                    text = "Ort: ${festivalData.location}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp),
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )

            }

            AnyeBottomBar(navController)
        }
    }
}


