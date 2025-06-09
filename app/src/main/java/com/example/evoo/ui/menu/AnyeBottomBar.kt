package com.example.evoo.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Box
import com.example.evoo.R
import com.example.evoo.ui.theme.colorthemetype.BottomDarkBlue
import androidx.compose.foundation.Image
import androidx.navigation.NavController
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.evoo.business.AuthManager

@Composable
fun rememberFakeNavController(): NavController {
    // Ein leerer Dummy-NavController zur Vorschau
    return NavController(LocalContext.current)
}

@Preview(showBackground = true)
@Composable
fun PreviewAnyeBottomBar() {
    AnyeBottomBar(
        navController = rememberFakeNavController()
    )
}




@Composable
fun AnyeBottomBar(navController: NavController)
//onHomeClick: () -> Unit,
//onSearchClick: () -> Unit,
//onProfileClick: () -> Unit,
//onSettingsClick: () -> Unit,
//onAnyeClick: () -> Unit
//)
{
    val currentUser = AuthManager.currentUser //Aktuellen Benutzer abrufen

    // Zustände für jedes Icon einzeln
    val (homeSelected, setHomeSelected) = remember { mutableStateOf(false) }
    val (searchSelected, setSearchSelected) = remember { mutableStateOf(false) }
    val (profileSelected, setProfileSelected) = remember { mutableStateOf(false) }
    val (settingsSelected, setSettingsSelected) = remember { mutableStateOf(false) }
    val (locationSelected, setLocationSelected) = remember { mutableStateOf(false) }

    // Aktuelle Route für dynamische Farbsteuerung
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Farbzustände basierend auf aktueller Route aktualisieren
    LaunchedEffect(currentRoute) {
        setHomeSelected(currentRoute == "HomeScreen")
        setSearchSelected(currentRoute == "SearchScreen")
        setProfileSelected(
            currentRoute?.startsWith("ProfileScreen1") == true ||
                    currentRoute == "LoginScreen" ||
                    currentRoute == "RegisterScreen"
        )
        setSettingsSelected(currentRoute == "SettingScreen")
        setLocationSelected(currentRoute == "LocationScreen")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(48.dp),
            //.padding(16.dp) ,
            containerColor = BottomDarkBlue.copy(alpha = 0.85f)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate("HomeScreen") }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Home",
                        tint = if (homeSelected) Color.Yellow else Color.White

                    )
                }
                IconButton(onClick = {navController.navigate("SearchScreen")}) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = if (searchSelected) Color.Yellow else Color.White
                    )
                }

                Spacer(modifier = Modifier.width(56.dp))

                IconButton(onClick = {
                    Log.d("Navigation","Navigating to ProfileScreen1")
                    if (currentUser != null) {
                        // Navigiere zum Profil mit Benutzernamen
                        navController.navigate("ProfileScreen1/${currentUser.name}")
                    } else {
                        // Fallback zur Login-Seite
                        navController.navigate("LoginScreen")
                    } }) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile",
                        tint = if (profileSelected) Color.Yellow else Color.White
                    )
                }
                IconButton(onClick = {navController.navigate("SettingScreen")}) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings",
                        tint = if (settingsSelected) Color.Yellow else Color.White
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .size(84.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-5).dp)
                .background(BottomDarkBlue.copy(alpha = 0.85f), CircleShape)
                .clickable { navController.navigate("LocationScreen") },
            //.shadow(2.dp, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_anye),
                contentDescription = "AnyE Logo",
                modifier = Modifier
                    .size(48.dp)
                    .offset(y = 2.dp),
                    colorFilter = if (locationSelected) {
                    ColorFilter.tint(Color.Yellow)
                } else {
                    ColorFilter.tint(Color.White)
                }
            )
        }
    }}