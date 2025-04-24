package com.example.evoo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.evoo.AccentColor
import com.example.evoo.BottomDarkBlue
import com.example.evoo.TopLightBlue
import com.example.evoo.ui.menu.MenuBar

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
                .background(brush = Brush.verticalGradient(colors = listOf(
                    TopLightBlue,
                    BottomDarkBlue
                )))
        ){
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Zurück",
                tint = Color.White,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .padding(24.dp)
                    .size(34.dp)
                    .clickable { navController.popBackStack() }
            )

            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text("Settings", fontSize = 50.sp, color = Color.White)
            }
            // Menu Bar
            MenuBar(navController)
        }
    }
}


