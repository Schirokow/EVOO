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
import com.example.evoo.ui.screens.ContentDetailScreen
import com.example.evoo.ui.screens.HomeScreen
import com.example.evoo.ui.screens.LocationScreen
import com.example.evoo.ui.screens.LoginScreen
import com.example.evoo.ui.screens.ProfileScreen1
import com.example.evoo.ui.screens.RegistrationScreen
import com.example.evoo.ui.screens.SettingScreen
import com.example.evoo.users.AuthManager


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
fun Navigation() {

    val navController = rememberNavController()
    val currentUser = AuthManager.currentUser
    val startDestination = if (currentUser != null) {
        "ProfileScreen1/${currentUser.name}" //Direkt zum Profil
    } else {
        "LoginScreen"
    }

    NavHost(
        navController = navController,
        startDestination = startDestination //Dynamische Startseite
    ) {
        composable("HomeScreen") { HomeScreen(navController) }
        composable("SettingScreen") { SettingScreen(navController) }
        composable("LoginScreen") { LoginScreen(navController) }
        composable("LocationScreen") { LocationScreen(navController) }
        composable("RegistrationScreen") { RegistrationScreen(navController) }

        composable(
            "ContentDetailScreen/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            ContentDetailScreen(navController, index)
        }

        composable(
            "ProfileScreen1/{userName}",
            arguments = listOf(navArgument("userName") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName")
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
