package com.example.evoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.evoo.ui.screens.AccountScreen
import com.example.evoo.ui.screens.ContentDetailScreen
import com.example.evoo.ui.screens.HomeScreen
import com.example.evoo.ui.screens.LocationScreen
import com.example.evoo.ui.screens.SettingScreen


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
    NavHost(navController = navController, startDestination = "HomeScreen"){
        composable ("HomeScreen"){ HomeScreen(navController)}
        composable ("SettingScreen"){ SettingScreen(navController)}
        composable ("AccountScreen"){ AccountScreen(navController)}
        composable ("LocationScreen"){ LocationScreen(navController)}
        composable (
            "ContentDetailScreen/{index}",
            arguments = listOf(navArgument("index"){type = NavType.IntType})
        ){ backStackEntry ->
                val index = backStackEntry.arguments?.getInt("index") ?:0
                ContentDetailScreen(navController, index)
            }
    }
}


//
// Selbstdefinierte Farben für Hintergrund und Vordergrund.
val BackgroundColor = Color(0xFF20587B)
val ForegroundColor = Color(0xFFED6E63)
val AccentColor = Color(0xFF29719E)
val BottomDarkBlue = Color(0xFF1A4D6C)
val TopLightBlue = Color(0xFF62A7C3)
