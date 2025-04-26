package com.example.evoo.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evoo.BackgroundColor
import com.example.evoo.ForegroundColor
import com.example.evoo.users.AuthManager

@Composable
fun MenuBar(navController: NavController){

    val currentUser = AuthManager.currentUser //Aktuellen Benutzer abrufen

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
                    .background(BackgroundColor),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Place,
                    contentDescription = "Explore",
                    tint = Color.White,
                    modifier = Modifier
                        .size(34.dp)
                        .clickable{
                            navController.navigate("LocationScreen")
                        }
                )
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = "Home",
                    tint = Color.White,
                    modifier = Modifier
                        .size(34.dp)
                        .clickable{ navController.navigate("HomeScreen")}
                )
                Icon(
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = "Account",
                    tint = Color.White,
                    modifier = Modifier
                        .size(34.dp)
                        .clickable{
                            if (currentUser != null) {
                                // Navigiere zum Profil mit Benutzernamen
                                navController.navigate("ProfileScreen1/${currentUser.name}")
                            } else {
                                // Fallback zur Login-Seite
                                navController.navigate("LoginScreen")
                            }
                        }
                )
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings",
                    tint = Color.White,
                    modifier = Modifier
                        .size(34.dp)
                        .clickable{navController.navigate("SettingScreen")}
                )
            }
        }
    }

}


