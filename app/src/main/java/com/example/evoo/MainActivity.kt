package com.example.evoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Navigation()

        }
    }
}

@Preview
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MainScreen"){
        composable ("MainScreen"){ MainScreen(navController)}
        composable ("SettingScreen"){ SettingScreen(navController)}
        composable ("AccountScreen"){ AccountScreen(navController)}
        composable ("LocationScreen"){ LocationScreen(navController)}
        composable ("ContentDetailScreen"){ ContentDetailScreen(navController)}
    }
}


// Startseite
@Composable
fun MainScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AccentColor)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues()) // Eine Function um den Content unter der Status Bar anzuzeigen.
                .background(BackgroundColor)
        ){
            // Funktion für die Vorschau.
            EventContent(navController)

            Box (
                modifier = Modifier
                    .padding(top = 6.dp)
            )
            {
                LogoImage()   // Function um Logo auf dem Screen darzustellen.
            }
            // Menu Bar
            MenuBar(navController)
        }
    }
}


@Composable
fun SettingScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AccentColor)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues()) // Eine Function um den Content unter der Status Bar anzuzeigen.
                .background(BackgroundColor)
        ){
            LogoImage()   // Function um Logo auf dem Screen darzustellen.

            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text("Einstellungen", fontSize = 50.sp)
            }
            // Menu Bar
            MenuBar(navController)
        }
    }
}


@Composable
fun AccountScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AccentColor)
    ){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues()) // Eine Function um den Content unter der Status Bar anzuzeigen.
                .background(BackgroundColor)
        ) {
            LogoImage()   // Function um Logo auf dem Screen darzustellen.

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 96.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Image(
                        painter = painterResource(R.drawable.avatar2),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(340.dp)
                            .clip(CircleShape)
                    )
                    Text("Anna Mustermann", fontSize = 24.sp, color = Color.White)
                    Text("Android-Entwicklerin aus Stuttgert", color = Color.Gray)
                }
            }
            // Menu Bar
            MenuBar(navController)
        }
    }

}


@Composable
fun LocationScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AccentColor)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues()) // Eine Function um den Content unter der Status Bar anzuzeigen.
                .background(BackgroundColor)
        ){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                GoogleMapScreen()
            }
            // Menu Bar
            MenuBar(navController)
        }
    }
}


@Composable
fun GoogleMapScreen() {
    val singapore = LatLng(1.3521, 103.8198) // Beispiel: Singapur Koordinaten
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 12f) // Zoom Level
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Füge eine Markierung hinzu
        Marker(
            state = MarkerState(position = singapore),
            title = "Singapur",
            snippet = "Hier ist Singapur!"
        )
    }
}


@Composable
fun ContentDetailScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AccentColor)
    ){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues()) // Eine Function um den Content unter der Status Bar anzuzeigen.
                .background(BackgroundColor)
        ){
            LogoImage()   // Function um Logo auf dem Screen darzustellen.

            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text("Content Detail", fontSize = 50.sp)
                // Menu Bar
                MenuBar(navController)
            }
        }
    }
}


@Composable
fun MenuBar(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        Card (
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(24.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-21).dp)
        ) {
            Row(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(0.7f)
                    .background(ForegroundColor),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Place,
                    contentDescription = "Explore",
                    modifier = Modifier
                        .size(34.dp)
                        .clickable{
                            navController.navigate("LocationScreen")
                        }
                )
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(34.dp)
                        .clickable{ navController.navigate("MainScreen")}
                )
                Icon(
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(34.dp)
                        .clickable{navController.navigate("AccountScreen")}
                )
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(34.dp)
                        .clickable{navController.navigate("SettingScreen")}
                )
            }
        }
    }

}


@Composable
fun EventContent(navController: NavController) {

        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items (30){ index ->
                Box(
                    modifier = Modifier
                        .size(width = 400.dp, height = 250.dp)
                        .padding(6.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.festival1),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable{
                                navController.navigate("ContentDetailScreen")
                            }

                    )
                }

                Box(
                    modifier = Modifier
                        .size(width = 400.dp, height = 250.dp)
                        .padding(6.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.festival2),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable{
                                navController.navigate("ContentDetailScreen")
                            }

                    )
                }

                Box(
                    modifier = Modifier
                        .size(width = 400.dp, height = 250.dp)
                        .padding(6.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.festival3),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable{
                                navController.navigate("ContentDetailScreen")
                            }

                    )
                }

                Box(
                    modifier = Modifier
                        .size(width = 400.dp, height = 250.dp)
                        .padding(6.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.festival4),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable{
                                navController.navigate("ContentDetailScreen")
                            }

                    )
                }

                Box(
                    modifier = Modifier
                        .size(width = 400.dp, height = 250.dp)
                        .padding(6.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.festival5),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable{
                                navController.navigate("ContentDetailScreen")
                            }

                    )
                }

                Box(
                    modifier = Modifier
                        .size(width = 400.dp, height = 250.dp)
                        .padding(6.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.festival6),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable{
                                navController.navigate("ContentDetailScreen")
                            }

                    )
                }
            }
        }
}


// Function um Logo auf dem Screen darzustellen
@Composable
fun LogoImage() {
    Box(
        modifier = Modifier
            .height(70.dp)
            .padding(3.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
    }
}


// Function um die Suchleiste darzustellen
@Composable
fun SearchBar() {
    // Zustand für den eingegebenen Text
    var searchText by remember { mutableStateOf("") }

    // Column als Container
    Column(
        modifier = Modifier
            //.fillMaxSize()
            .offset(x = 0.dp, y = 85.dp)
            .padding(24.dp)
            .border(3.dp, ForegroundColor)
    ) {
        // Das Textfeld als Suchleiste
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Suchen...") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Such-Icon")
            },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(onClick = { searchText = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Leeren")
                    }
                }
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
// Neue Zeile
// Selbstdefinierte Farben für Hintergrund und Vordergrund.
val BackgroundColor = Color(0xFF20587B)
val ForegroundColor = Color(0xFFED6E63)
val AccentColor = Color(0xFF29719E)
