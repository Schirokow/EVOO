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
import androidx.compose.ui.draw.shadow

@Preview
@Composable
fun PreviewAnyeBottomBar() {
    AnyeBottomBar(
        onHomeClick = {},
        onSearchClick = {},
        onProfileClick = {},
        onSettingsClick = {},
        onAnyeClick = {}
    )
}




@Composable
fun AnyeBottomBar(
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onAnyeClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp) ,
            containerColor = BottomDarkBlue
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onHomeClick) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Home",
                        tint = Color.White
                    )
                }
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(56.dp))

                IconButton(onClick = onProfileClick) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile",
                        tint = Color.White
                    )
                }
                IconButton(onClick = onSettingsClick) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings",
                        tint = Color.White
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .size(96.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-25).dp)
                .background(BottomDarkBlue, CircleShape)
                .clickable {onAnyeClick()}
                .shadow(4.dp, CircleShape ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_anye),
                contentDescription = "AnyE Logo",
                modifier = Modifier.size(58.dp)
            )
        }
    }}