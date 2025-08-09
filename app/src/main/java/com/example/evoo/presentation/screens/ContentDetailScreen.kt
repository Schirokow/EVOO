package com.example.evoo.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.evoo.AccentColor
import com.example.evoo.BottomDarkBlue
import com.example.evoo.TopLightBlue
import com.example.evoo.data.AppModule
import com.example.evoo.presentation.viewmodels.ContentDetailViewModel
import com.example.evoo.ui.components.buttons.ClickButton
import com.example.evoo.ui.menu.AnyeBottomBar
import coil.compose.AsyncImage
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun ContentDetailScreen(navController: NavController, id: String){
    val TAG = "ContentDetailScreen"
    Log.d(TAG, "Screen initialized with id: $id")

//    val viewModel: ContentDetailViewModel = viewModel()

    val context = LocalContext.current
    val viewModel: ContentDetailViewModel = viewModel(factory = AppModule.provideDetailViewModelFactory(context))

    LaunchedEffect(id) {
        Log.d(TAG, "Loading festival for id: $id")
        viewModel.loadEvent(id)
    }

//    val festival by viewModel.festival.collectAsState()
    val event by viewModel.event.collectAsState()
//    val isFavorite by viewModel.isFavorite.collectAsState()
    if (event == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AccentColor),
            ) {
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.systemBars.asPaddingValues())
                    .background(brush = Brush.verticalGradient(colors = listOf(
                        TopLightBlue,
                        BottomDarkBlue
                    ))),
                contentAlignment = Alignment.Center
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Zurück",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(24.dp)
                            .size(34.dp)
                            .clickable { navController.popBackStack() }
                    )
                }
                Text("Event nicht gefunden", color = Color.White, fontSize = 24.sp)
            }
            return
            }

    }

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

//            Icon(
//                imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
//                contentDescription = "Favorite",
//                tint = if (isFavorite) Color.Yellow else Color.White,
//                modifier = Modifier
//                    .align(alignment = Alignment.TopEnd)
//                    .padding(24.dp)
//                    .size(34.dp)
//                    .clickable{
//                        Log.i(TAG, "Favorite clicked for: ${festival!!.title.take(15)}...")
//                        viewModel.toggleFavorite(festival!!)
//                    }
//            )

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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp,start = 16.dp, end = 16.dp),
                contentPadding = PaddingValues(bottom = 80.dp), // Platz für AnyeBottomBar
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    Log.d(TAG, "Rendering content for: ${event?.name?.take(15)}...")

                    // Bild
//                    Card (
//                        modifier = Modifier
//                            .fillMaxWidth(0.9f)
////                            .fillMaxHeight(0.5f),
//                            .height(300.dp),
//                        shape = RoundedCornerShape(16.dp),
//                        elevation = CardDefaults.cardElevation(12.dp)
//                    ){
//                        Image(
//                            painter = painterResource(id = festival?.imageId ?: 0),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .fillMaxSize(),
//                            contentScale = ContentScale.FillBounds
//                        )
//                    }
                    // Bild mit AsyncImage direkt laden
                    val imageUrl = event?.images?.firstOrNull()?.url
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(12.dp)
                    ) {
                        if (imageUrl != null) {
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = "Event Image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }


                    // Titel
                    Text(
                        text = event?.name.toString(),
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )

//                    // Beschreibung
//                    Text(
//                        text = "Beschreibung: ${festival?.description}",
//                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp),
//                        color = Color.White,
//                        modifier = Modifier.padding(16.dp)
//                    )
//
//                    // Datum
//                    Text(
//                        text = "Datum: ${festival?.datum}",
//                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp),
//                        color = Color.White,
//                        modifier = Modifier.padding(16.dp)
//                    )

                    // Datum (verwende direkt den String)
                    Text(
                        text = "Datum: ${event?.dates?.start?.localDate ?: "N/A"}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp),
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                    // Ort (aus dem _embedded-Objekt holen)
                    val venueName = event?._embedded?.venues?.firstOrNull()?.name ?: "Unbekannter Ort"
                    Text(
                        text = "Ort: $venueName",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp),
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )



                    // Link zum Ticketmaster-Event (als klickbarer Text)
                    event?.url?.let { url ->
                        Text(
                            text = "Tickets kaufen: $url",
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp),
                            color = Color.Blue, // oder eine andere Farbe, um den Link zu betonen
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    // Logik für den Klick auf den Link
                                    // Hier kannst du einen Intent zum Öffnen des Browsers starten
                                }
                        )
                    }


                    // Location
//                    Text(
//                        text = "Ort: ${festival?.location}",
//                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp),
//                        color = Color.White,
//                        modifier = Modifier.padding(16.dp)
//                    )

                    // Spacer für minimale Scroll-Länge
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                    ) // Definiert die zusätzliche Scroll-Länge

                }
                }


            AnyeBottomBar(navController)
        }
    }
}


