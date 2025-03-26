package com.example.evoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition.Center.position
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
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
            //MainScreen() // Hier wird die Startseite aufgerufen.
            Navigation()

        }
    }
}


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MainScreen"){
        composable ("MainScreen"){ MainScreen(navController)}
        composable ("SetingScreen"){ SetingScreen(navController)}
        composable ("AccountScreen"){ AccountScreen(navController)}
        composable ("LocationScreen"){ LocationScreen(navController)}
        composable ("ContentScreen"){ ContentScreen(navController)}
    }
}


// Startseite
//@Preview(showBackground = true)
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
            LogoImage()   // Function um Logo auf dem Screen darzustellen.
            SearchBar()  // Function um die Suchleiste darzustellen

            // Zwei Funktionen für die Vorschau (Jede in eigener Box)
            Box (
                modifier = Modifier
                    .padding(top = 200.dp)
                    .clickable{navController.navigate("ContentScreen")}
            ) {
                Vorschau()
            }

            Box (
                modifier = Modifier.padding(top = 450.dp)
            ) {
                Vorschau()
            }

            // Menu Bar
            Card (
                shape = RoundedCornerShape(28.dp),
                elevation = CardDefaults.cardElevation(24.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-32).dp)
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
                            .clickable{navController.navigate("LocationScreen")}
                    )
                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = "Account",
                        modifier = Modifier
                            .size(34.dp)
                            .clickable{navController.navigate("AccountScreen")}
                    )
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier
                            .size(34.dp)
                            .clickable{
                                 navController.navigate("SetingScreen")


                            }
                    )
                }
            }
        }


    }

}

//@Preview(showBackground = true)
@Composable
fun SetingScreen(navController: NavController){
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
            Card (
                shape = RoundedCornerShape(28.dp),
                elevation = CardDefaults.cardElevation(24.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-32).dp)
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
                            .clickable{navController.navigate("LocationScreen")}
                    )
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Home",
                        modifier = Modifier
                            .size(34.dp)
                            .clickable{ navController.navigate("MainScreen")}
                    )
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier
                            .size(34.dp)
                            .clickable{

                            }
                    )
                }
            }
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
        ){
            LogoImage()   // Function um Logo auf dem Screen darzustellen.

            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text("Account", fontSize = 50.sp)
            }


            // Menu Bar
            Card (
                shape = RoundedCornerShape(28.dp),
                elevation = CardDefaults.cardElevation(24.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-32).dp)
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
                            .clickable{navController.navigate("LocationScreen")}
                    )
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Home",
                        modifier = Modifier
                            .size(34.dp)
                            .clickable{ navController.navigate("MainScreen")}
                    )
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier
                            .size(34.dp)
                            .clickable{navController.navigate("SetingScreen")}
                    )
                }
            }
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),  // Abstand zur Status Bar
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //Spacer(modifier = Modifier.height(16.dp))
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(24.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.98f)
                        .heightIn(300.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        GoogleMapScreen()
                    }
                }
            }

            // Menu Bar
            Card (
                shape = RoundedCornerShape(28.dp),
                elevation = CardDefaults.cardElevation(24.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-32).dp)
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
                            .clickable{}
                    )
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Home",
                        modifier = Modifier
                            .size(34.dp)
                            .clickable{ navController.navigate("MainScreen")}
                    )
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier
                            .size(34.dp)
                            .clickable{navController.navigate("SetingScreen")}
                    )
                }
            }
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
fun ContentScreen(navController: NavController){
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
            }


            // Menu Bar
            Card (
                shape = RoundedCornerShape(28.dp),
                elevation = CardDefaults.cardElevation(24.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-32).dp)
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
                            .clickable{}
                    )
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Home",
                        modifier = Modifier
                            .size(34.dp)
                            .clickable{ navController.navigate("MainScreen")}
                    )
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier
                            .size(34.dp)
                            .clickable{navController.navigate("SetingScreen")}
                    )
                }
            }
        }


    }

}


@Composable
fun Vorschau() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(243.dp)
            .padding(16.dp)
    ) {
        val pagerState = rememberPagerState(initialPage = 5) { 10 }

        HorizontalPager(

            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .clickable{},
                elevation = CardDefaults.cardElevation(16.dp)

            ) {
                // Bild für die Vorschau
                Box(
                    modifier = Modifier
                        .background(BackgroundColor)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.festival1),
                        contentDescription = null
                    )
                    /*Text(
                    text = "Bild $page",
                    style = MaterialTheme.typography.headlineMedium
                )*/
                }
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

// Selbstdefinierte Farben für Hintergrund und Vordergrund.
val BackgroundColor = Color(0xFF20587B)
val ForegroundColor = Color(0xFFED6E63)
val AccentColor = Color(0xFF29719E)