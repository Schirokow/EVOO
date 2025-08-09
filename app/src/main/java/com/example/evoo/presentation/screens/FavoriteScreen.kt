package com.example.evoo.presentation.screens

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import com.example.evoo.data.Favorite
import com.example.evoo.presentation.viewmodels.FavoriteViewModel
import com.example.evoo.ui.components.buttons.ClickButton
import com.example.evoo.ui.components.card.NewEventCard
import com.example.evoo.ui.menu.AnyeBottomBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController){
    val TAG = "FavoriteScreen"
    Log.d(TAG, "Favorite screen initialized")

    val context = LocalContext.current
    val viewModel: FavoriteViewModel = viewModel(factory = AppModule.provideFavoriteViewModelFactory(context))
    val favoriteEvents by viewModel.favoriteEvents.collectAsState()
    var showDeleteAllDialog by remember { mutableStateOf(false) }

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
                Spacer(modifier = Modifier.width(50.dp))
                ClickButton(
                    text = "Alle löschen",
                    onClick = { showDeleteAllDialog = true },
                    modifier = Modifier.width(150.dp)
                )
            }
            if (favoriteEvents.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Keine Favoriten vorhanden", color = Color.White, fontSize = 20.sp)
                }
            } else {
                FavoriteContent(navController, viewModel)
            }
            AnyeBottomBar(navController)
        }
    }
    if (showDeleteAllDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteAllDialog = false },
            title = { Text("Alle Favoriten löschen?") },
            text = { Text("Möchtest du wirklich alle Favoriten aus der Datenbank löschen?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteAllFavorites()
                        showDeleteAllDialog = false
                        Log.d(TAG, "All favorites deleted")
                    }
                ) {
                    Text("Löschen")
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteAllDialog = false }) {
                    Text("Abbrechen")
                }
            }
        )
    }

}
@Composable
fun FavoriteContent(navController: NavController, viewModel: FavoriteViewModel) {
    val TAG = "FavoriteContent"
    val favoriteEvents by viewModel.favoriteEvents.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val favoriteStates = remember { mutableStateMapOf<String, Boolean>() }

    LaunchedEffect(favoriteEvents) {
        favoriteEvents.forEach { event ->
            favoriteStates[event.eventId] = true // Alle Festivals hier sind Favoriten
        }
    }

    var selectedEventData by remember { mutableStateOf<Favorite?>(null) }
    Log.d(TAG, "Rendering favorite grid with ${favoriteEvents.size} items")

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(favoriteEvents) { index, event ->
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .aspectRatio(1f)
            ) {

                NewEventCard(
                    imageUrl = event.imageUrl,
                    title = event.name,
                    datum = event.date,
                    onClick = {
                        Log.d(TAG, "Event card clicked - id: ${event.eventId}, title: ${event.name.take(15)}...")
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

    selectedEventData?.let { event ->
        Log.d(TAG, "Showing detail overlay for id: ${event.eventId}")
        Surface(
            color = BackgroundColor.copy(alpha = 0.9f),
            modifier = Modifier.fillMaxSize(),
            onClick = { /*selectedFestivalData = null */ }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp),
//                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    NewEventCard(
                        imageUrl = event.imageUrl,
                        title = event.name,
                        datum = event.date,
                        onClick = {
                            Log.d(TAG, "Navigating to detail screen for id: ${event.eventId}")
                            navController.navigate("ContentDetailScreen/${event.eventId}")
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
                        .clickable {
                            Log.d(TAG, "Close button clicked, hiding detail view")
                            selectedEventData = null
                        }
                )
                Icon(
                    imageVector = if (favoriteStates[event.eventId] == true) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (favoriteStates[event.eventId] == true) Color.Yellow else Color.White,
                    modifier = Modifier
                        .align(alignment = Alignment.TopEnd)
                        .padding(24.dp)
                        .size(34.dp)
                        .clickable {
                            Log.i(TAG, "Favorite button clicked for id: ${event.eventId}")
                            viewModel.toggleFavorite(event)
                            favoriteStates[event.eventId] =
                                !(favoriteStates[event.eventId] ?: false)
                        }
                )
            }
        }
    }
}


