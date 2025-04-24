package com.example.evoo.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.evoo.R
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.layout.ContentScale
import com.example.evoo.ui.components.button.EditIconButton
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.evoo.AccentColor
import com.example.evoo.model.EventTab
import com.example.evoo.model.sampleEvents
import com.example.evoo.ui.components.buttons.ClickButton
import com.example.evoo.ui.components.card.EventCard
import com.example.evoo.ui.menu.MenuBar
import com.example.evoo.users.UsersRepository


@Composable
fun ProfileScreen1 (navController: NavController, userName: String?) {
    var selectedTab by remember { mutableStateOf(EventTab.All) }
    val displayedEvents = when (selectedTab) {
        EventTab.All -> sampleEvents
        EventTab.Favorites -> sampleEvents.take(2)
        EventTab.Liked -> sampleEvents.takeLast(2)
    }
    var name by remember { mutableStateOf("") }

    val itemsPerRow = 3

    // Finde den User in der Repository
    val user = UsersRepository.userData.find { it.name == userName }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AccentColor)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues())
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(TopLightBlue, BottomDarkBlue)
                    )
                )
        ) {
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(50.dp))

                // NameFeld

                Text(
                    text = "Willkommen ${user?.name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )


                Spacer(modifier = Modifier.height(20.dp))


                //Profilbild mit Edit

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = user?.profilePicture ?: R.drawable.default_avatar),
                        contentDescription = "ProfilePicture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(4.dp, Color.White, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
                Box(
                    modifier = Modifier
                        .offset(x = 36.dp, y = -24.dp)
                ) {
                    EditIconButton(
                        modifier = Modifier
                            .size(24.dp)

                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                ClickButton(
                    text = "Edit Profile",
                    onClick = {},
                    modifier = Modifier
                )

                TabRow(
                    selectedTabIndex = selectedTab.ordinal,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Color.Transparent
                ) {
                    EventTab.entries.forEach { tab ->
                        Tab(
                            selected = selectedTab == tab,
                            onClick = { selectedTab = tab },
                            icon = {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = tab.label,
                                    tint = if (selectedTab == tab) Color.White else Color.DarkGray
                                )
                            }
                        )
                    }
                }
                //HorizontalDivider(
                //  color = Color.LightGray,
                //thickness = 5.dp,
                //modifier = Modifier.padding(vertical = 8.dp)
                //)

                LazyVerticalGrid(
                    columns = GridCells.Fixed(itemsPerRow),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)

                ) {
                    items(displayedEvents) { event ->
                        EventCard(
                            event = event,
                            onClick = {},
                            isLarge = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                        )
                    }
                }
            }
            MenuBar(navController)
        }

    }


}