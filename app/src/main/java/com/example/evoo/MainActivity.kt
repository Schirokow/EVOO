package com.example.evoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Place
import androidx.compose.material.icons.twotone.Settings
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen() // Hier wird die Startseite aufgerufen.


        }
    }
}



// Startseite
@Preview(showBackground = true)
@Composable
fun MainScreen(){
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
                        .fillMaxWidth(0.8f)
                        .background(ForegroundColor),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        imageVector = Icons.Rounded.Place,
                        contentDescription = "Explore",
                        modifier = Modifier.size(34.dp)
                    )
                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = "Account",
                        modifier = Modifier.size(34.dp)
                    )
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(34.dp)
                    )
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
            .fillMaxSize()
            .padding(3.dp)
            .offset(y = (-380).dp)


    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.align(Alignment.Center)
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
            .fillMaxSize()
            .offset(x = 0.dp, y = 85.dp)
            .padding(24.dp)

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

