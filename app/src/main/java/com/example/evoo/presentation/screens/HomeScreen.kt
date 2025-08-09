package com.example.evoo.presentation.screens

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.evoo.AccentColor
import com.example.evoo.BottomDarkBlue
import com.example.evoo.TopLightBlue
import com.example.evoo.data.AppModule
import com.example.evoo.data.FestivalData
import com.example.evoo.presentation.viewmodels.HomeViewModel
import com.example.evoo.ui.components.buttons.ClickButton
import com.example.evoo.ui.components.card.NewEventCard
import com.example.evoo.ui.menu.AnyeBottomBar
import kotlinx.coroutines.launch

// Für Bildanzeige
import coil.compose.AsyncImage

import androidx.compose.ui.draw.clip
import androidx.compose.foundation.clickable
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.text.TextStyle
import androidx.room.util.TableInfo
import com.example.evoo.data.TicketmasterDates
import com.example.evoo.data.TicketmasterEvent


// Startseite
@Composable
fun HomeScreen(navController: NavController){
    val TAG = "HomeScreen"
    Log.d(TAG, "Home screen initialized")

//    val viewModel: HomeViewModel = viewModel()

    val context = LocalContext.current
    val viewModel: HomeViewModel = viewModel(factory = AppModule.provideHomeViewModelFactory(context))
    var showDeleteDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var city by remember {
        mutableStateOf("")
    }

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
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
            ){
                OutlinedTextField(
                    value = city,
                    singleLine = true,
                    placeholder = {Text("Stadt eingeben", color = Color.White)},
                    textStyle = TextStyle(color = Color.White),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Yellow,
                        unfocusedBorderColor = Color.White
                    ),
                    onValueChange = { city = it },
                    modifier = Modifier.padding(start = 65.dp)
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    ClickButton(
                        text = "Laden",
                        onClick = {
                            if (city.isNotBlank()){
//                            viewModel.loadAllFestivals()
                                viewModel.loadAllEvents(city)
                                city = ""
                            }

                        },
                        modifier = Modifier.width(150.dp)
                    )

                    ClickButton(
                        text = "Löschen",
                        onClick = {showDeleteDialog = true},
                        modifier = Modifier.width(150.dp)
                    )

                }

            }
            // Funktion für die Vorschau.
            EventContent(navController, viewModel)

            AnyeBottomBar(navController)
        }
    }
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Alle Events löschen?") },
            text = { Text("Möchtest du wirklich alle Events aus der Liste löschen? Favoriten bleiben erhalten.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteAllEvents()
                        showDeleteDialog = false
                        Log.d(TAG, "All events deleted")
                    }
                ) {
                    Text("Löschen")
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteDialog = false }) {
                    Text("Abbrechen")
                }
            }
        )
    }
}



@Composable
fun EventContent(navController: NavController,viewModel: HomeViewModel) {

    val TAG = "EventContent"
    val festivalDataList by viewModel.festivalData.collectAsState()
    val eventsDataList by viewModel.eventsData.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val favoriteStates = remember { mutableStateMapOf<Int, Boolean>() }

    LaunchedEffect(festivalDataList) {
        festivalDataList.forEach { festival ->
            coroutineScope.launch {
                val isFavorite = viewModel.favoriteRepository.isFavorite(festival.id)
                favoriteStates[festival.id] = isFavorite
            }
        }
    }

    // Schutz vor leeren Listen
    if (
//        festivalDataList.isEmpty()
        eventsDataList.isEmpty()
        ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Keine Events geladen", color = Color.White, fontSize = 20.sp)
        }
        return
    }

    // State, um ausgewählte EventData zu speichern
    var selectedEventData by remember { mutableStateOf<TicketmasterEvent?>(null).also {
        Log.d(TAG, "Selected festival state initialized")
    } }

    Log.d(TAG, "Rendering festival grid with ${festivalDataList.size} items")

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 155.dp),
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 500.dp
        ),
        columns = GridCells.Fixed(2)
    ){
        itemsIndexed(eventsDataList){ index, event ->

            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .aspectRatio(1f)
            ){
                NewEventCard(
                    image = event.images,
                    title = event.name,
                    datum = event.dates?.start?.localDate,
                    onClick = {
                        Log.d(TAG, "Festival card clicked - id: ${event.id}, title: ${event.name.take(15)}...")
                            selectedEventData = event
                    },
                    isLarge = true,
                    modifier = Modifier
                )
            }
        }
    }

    val animateScale by animateFloatAsState(
        targetValue = if (selectedEventData != null) 1f else 0.5f,
        animationSpec = tween(durationMillis = 400)
    )

    // Overlay für vergrößertes Bild, wenn selectedEventData nicht null ist
    selectedEventData?.let { event ->
        Log.d(TAG, "Showing detail overlay for id: ${event.id}")

        Surface(
            color = BackgroundColor.copy(alpha = 0.9f), // Farbe von Hintergrund
            modifier = Modifier.fillMaxSize(),
            onClick = { /*selectedEventData = null */} // Klick außerhalb schließt das Overlay
        ) {
            Box (
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp),
//                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    NewEventCard(
                        image = event.images,
                        title = event.name,
                        datum = event.dates?.start?.localDate,
                        onClick = {
                            Log.d(TAG, "Navigating to detail screen for id: ${event.id}")
                                navController.navigate("ContentDetailScreen/${event.id}")
                        },
                        isLarge = true,
                        textIsLarge = true,
                        modifier = Modifier
                            .graphicsLayer(scaleX = animateScale, scaleY = animateScale)
                            .fillMaxWidth(0.9f)
//                            .fillMaxHeight(0.5f)
                            .height(300.dp)
                    )
                }

                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(24.dp)
                        .size(34.dp)
                        .clickable{
                            Log.d(TAG, "Close button clicked, hiding detail view")
                            selectedEventData = null
                        }
                    )

//                Icon(
//                    imageVector = if (favoriteStates[festival.id] == true) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
//                    contentDescription = "Favorite",
//                    tint = if (favoriteStates[festival.id] == true) Color.Yellow else Color.White,
//                    modifier = Modifier
//                        .align(alignment = Alignment.TopEnd)
//                        .padding(24.dp)
//                        .size(34.dp)
//                        .clickable{
//                            Log.i(TAG, "Favorite button clicked for id: ${festival.id}")
//                            viewModel.toggleFavorite(festival)
//                            favoriteStates[festival.id] = !(favoriteStates[festival.id] ?: false)
//                        }
//                )
                //Erklärung:
                //Der Favoritenstatus wird mit favoriteStates (eine mutableStateMapOf) dynamisch geladen, um UI-Reaktivität zu gewährleisten.
                //LaunchedEffect lädt den Favoritenstatus für jedes Festival beim Rendern.
                //Der Favoriten-Button toggelt den Status und aktualisiert favoriteStates.
            }

        }
    }

}

val BackgroundColor = Color(0xFF20587B)


