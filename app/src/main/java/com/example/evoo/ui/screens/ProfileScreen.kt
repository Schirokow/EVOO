

// ProfileScreen.kt
package com.example.evoo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.evoo.ui.components.header.HeaderBar
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import com.example.evoo.R
import androidx.compose.ui.draw.clip
import com.example.evoo.ui.components.buttons.ClickButton


val AccentColor = Color(0xFF29719E)
val BottomDarkBlue = Color(0xFF1A4D6C)
val TopLightBlue = Color(0xFF62A7C3)
val ForegroundColor = Color(0xFFED6E63)

@Preview(showBackground = true)
@Composable
fun ProfileScreen() {

    var name by remember { mutableStateOf("A.Hansen") }
    var email by remember { mutableStateOf("hansenj@gmail.com") }
    var password by remember {mutableStateOf("geheim123")}

    var isEditingName by remember {mutableStateOf(false)}
    var isEditingEmail by remember {mutableStateOf(false)}
    var isEditingPassword by remember {mutableStateOf(false)}



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(TopLightBlue,BottomDarkBlue))
            )

    )

    {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderBar()
            Spacer(modifier = Modifier.height(32.dp))

            //Profilbild
            Image(
                painter = painterResource (id = R.drawable.avatar2),
                contentDescription = "ProfilePicture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)

            )

            Spacer(modifier = Modifier.height(8.dp))

            //Name
            Text(text = name, style = MaterialTheme.typography.headlineSmall,color = Color.White)

            Spacer(modifier = Modifier.height(32.dp))

            //Profilinformationen

            ProfileField(
                    label = "Name" ,
                    value = name,
                    isEditing = isEditingName,
                    onEdit = { isEditingName = true },
                    onValueChange = { newValue ->
                            name = newValue
                            isEditingName = false
                }
            )

            ProfileField(
                label = "E-mail" ,
                value = email,
                isEditing = isEditingEmail,
                onEdit = { isEditingEmail = true },
                onValueChange = { newValue ->
                        email = newValue
                        isEditingName = false
                }
            )

            ProfileField(
                label = "Passwort" ,
                value = password,
                isEditing = isEditingPassword,
                onEdit = { isEditingPassword = true },
            onValueChange = { newValue ->
                    password = newValue
                    isEditingName = false

                }
            )

        }
    }
}

@Composable
fun ProfileField(
    label: String,
    value: String,
    isEditing: Boolean,
    onEdit: () -> Unit,
    onValueChange: (String) -> Unit
)
{
    Column(modifier = Modifier.padding(8.dp)){
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = Color.White)
        Spacer(modifier = Modifier.height(4.dp))

        if (isEditing) {
            TextField(
                value = value,
                onValueChange = { onValueChange(it) },
                singleLine = true
            )
        } else {
            Text(text = value, color = Color.White, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(4.dp))

        ClickButton(
            text = "Bearbeiten",
            onClick = onEdit
                )
        }
    }
