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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.evoo.AccentColor
import com.example.evoo.BottomDarkBlue
import com.example.evoo.TopLightBlue
import com.example.evoo.data.FestivalData
import com.example.evoo.presentation.viewmodels.AppModule
import com.example.evoo.presentation.viewmodels.HomeViewModel
import com.example.evoo.ui.menu.AnyeBottomBar



// Startseite
@Composable
fun HomeScreen(navController: NavController){
    val TAG = "HomeScreen"
    Log.d(TAG, "Home screen initialized")

    val viewModel: HomeViewModel = viewModel(factory = AppModule.homeViewModelFactory)

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
            EventContent(navController, viewModel)

            AnyeBottomBar(navController)
        }
    }
}


@Composable
fun EventContent(navController: NavController,viewModel: HomeViewModel) {

    val TAG = "EventContent"

    val festivalDataList by viewModel.festivalData.collectAsState()

    // Schutz vor leeren Listen
    if (festivalDataList.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    // State, um ausgewählte FestivalData zu speichern
    var selectedFestivalData by remember { mutableStateOf<FestivalData?>(null).also {
        Log.d(TAG, "Selected festival state initialized")
    } }

    Log.d(TAG, "Rendering festival grid with ${festivalDataList.size} items")

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        columns = GridCells.Fixed(2)
    ){
        itemsIndexed(festivalDataList){ index, festival ->

            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .aspectRatio(1f)
            ){
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable{
                            Log.d(TAG, "Festival card clicked - id: ${festival.id}, title: ${festival.title.take(15)}...")
                            selectedFestivalData = festival
                                  },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = festival.imageId),
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
        targetValue = if (selectedFestivalData != null) 1f else 0.5f,
        animationSpec = tween(durationMillis = 400)
    )

    // Overlay für vergrößertes Bild, wenn selectedFestivalData nicht null ist
    selectedFestivalData?.let { festival ->
        Log.d(TAG, "Showing detail overlay for id: ${festival.id}")
//        val festivalData = festivalData // Datenobjekt via Index

        Surface(
            color = BackgroundColor.copy(alpha = 0.9f), // Farbe von Hintergrund
            modifier = Modifier.fillMaxSize(),
            onClick = { /*selectedFestivalData = null */} // Klick außerhalb schließt das Overlay
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
                        text = festival.title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Card (
                        modifier = Modifier
                            .graphicsLayer(scaleX = animateScale, scaleY = animateScale)
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.5f)
                            .clickable{
                                Log.d(TAG, "Navigating to detail screen for id: ${festival.id}")
                                navController.navigate("ContentDetailScreen/${festival.id}")
                                      },
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(12.dp)
                    ){
                        Image(
                            painter = painterResource(id = festival.imageId),
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
                        .clickable{
                            Log.d(TAG, "Close button clicked, hiding detail view")
                            selectedFestivalData = null
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
                            Log.i(TAG, "Favorite button clicked for id: ${festival.id}")
                        }
                )
            }

        }
    }

}

val BackgroundColor = Color(0xFF20587B)


