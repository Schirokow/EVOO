package com.example.evoo.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector




enum class EventTab (
    val label: String,
    val icon: ImageVector
) {
    All("Alle", Icons.Default.Home),
    Favorites("Favoriten", Icons.Default.Star),
    Liked("Herz", Icons.Default.Favorite)
}