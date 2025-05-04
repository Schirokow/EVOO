package com.example.evoo

import android.os.Bundle
import android.util.Log
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
import com.example.evoo.ui.screens.ContentDetailScreen
import com.example.evoo.ui.screens.HomeScreen
import com.example.evoo.ui.screens.LocationScreen
import com.example.evoo.ui.screens.LoginScreen
import com.example.evoo.ui.screens.ProfileScreen1
import com.example.evoo.ui.screens.RegistrationScreen
import com.example.evoo.ui.screens.SettingScreen
import com.example.evoo.users.AuthManager

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Activity created")
        enableEdgeToEdge()
        setContent {
            Log.d(TAG, "Composing UI content")
            Navigation()

        }
    }
}

@Preview
@Composable
fun Navigation() {

    val TAG = "AppNavigation"
    val navController = rememberNavController()
    val currentUser = AuthManager.currentUser
    val startDestination = if (currentUser != null) {
        Log.i(TAG, "User authenticated: ${currentUser.name.take(3)}...")
        "ProfileScreen1/${currentUser.name}" //Direkt zum Profil
    } else {
        Log.i(TAG, "No authenticated user found")
        "LoginScreen"
    }

    Log.d(TAG, "Initializing navigation with start destination: $startDestination")

    NavHost(
        navController = navController,
        startDestination = startDestination //Dynamische Startseite
    ) {
        composable("HomeScreen") {
            Log.d(TAG, "Navigating to HomeScreen")
            HomeScreen(navController)
        }
        composable("SettingScreen") {
            Log.d(TAG, "Navigating to SettingScreen")
            SettingScreen(navController)
        }
        composable("LoginScreen") {
            Log.d(TAG, "Navigating to LoginScreen")
            LoginScreen(navController)
        }
        composable("LocationScreen") {
            Log.d(TAG, "Navigating to LocationScreen")
            LocationScreen(navController)
        }
        composable("RegistrationScreen") {
            Log.d(TAG, "Navigating to RegistrationScreen")
            RegistrationScreen(navController)
        }

        composable(
            "ContentDetailScreen/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            Log.d(TAG, "Navigating to ContentDetailScreen with index: $index")
            ContentDetailScreen(navController, index)
        }

        composable(
            "ProfileScreen1/{userName}",
            arguments = listOf(navArgument("userName") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName")
            Log.d(TAG, "Navigating to ProfileScreen1 for user: ${userName?.take(3)}...")
            ProfileScreen1(navController, userName)
        }
    }
}

//Navigationsmechanismus:
//
//{userName}: Platzhalter für Parameter
//navArgument: Definiert den erwarteten Typ
//backStackEntry.arguments: Zugriff auf übergebene Parameter
//Sicherer Zugriff mit Elvis-Operator: ?: "Default"

// Selbstdefinierte Farben für Hintergrund und Vordergrund.
val BackgroundColor = Color(0xFF20587B)
val ForegroundColor = Color(0xFFED6E63)
val AccentColor = Color(0xFF29719E)
val BottomDarkBlue = Color(0xFF1A4D6C)
val TopLightBlue = Color(0xFF62A7C3)
