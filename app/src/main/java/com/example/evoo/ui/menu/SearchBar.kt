package com.example.evoo.ui.menu

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evoo.ForegroundColor

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