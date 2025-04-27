package com.example.evoo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.evoo.AccentColor
import com.example.evoo.BottomDarkBlue
import com.example.evoo.R
import com.example.evoo.TopLightBlue
import com.example.evoo.ui.components.buttons.ClickButton
import com.example.evoo.ui.menu.MenuBar
import com.example.evoo.users.AuthManager
import com.example.evoo.users.User
import com.example.evoo.users.UsersRepository

@Composable
fun RegistrationScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AccentColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues()) // Eine Function um den Content unter der Status Bar anzuzeigen.
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            TopLightBlue,
                            BottomDarkBlue
                        )
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

            val userNameState = remember { mutableStateOf(TextFieldValue()) }
            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val passwordState = remember { mutableStateOf(TextFieldValue()) }
            val repeatPasswordState = remember { mutableStateOf(TextFieldValue()) }

            var registrationError by remember { mutableStateOf<RegistrationError?>(null) }

            if (registrationError != null) {
                AlertDialog(
                    onDismissRequest = { registrationError = null },
                    title = { Text("Registrierungsfehler") },
                    text = { Text(registrationError!!.message) },
                    confirmButton = {
                        Button(onClick = { registrationError = null }) {
                            Text("OK")
                        }
                    }
                )
            }



            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    "Registrierung",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Image(
                    painter = painterResource(id = R.drawable.logo_anye),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .size(120.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    "Event Up Your Life",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(35.dp))

                TextField(
                    value = userNameState.value,
                    onValueChange = { newText -> userNameState.value = newText },
                    label = { Text("Benutzername") },
                    placeholder = { Text("Benutzername") },
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )

                TextField(
                    value = emailState.value,
                    onValueChange = { newText -> emailState.value = newText },
                    label = { Text("E-Mail") },
                    placeholder = { Text("E-Mail") },
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
                TextField(
                    value = passwordState.value,
                    onValueChange = { newText -> passwordState.value = newText },
                    label = { Text("Passwort") },
                    placeholder = { Text("Passwort") },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
                TextField(
                    value = repeatPasswordState.value,
                    onValueChange = { repeatPasswordState.value = it },
                    label = { Text("Passwort wiederholen") },
                    placeholder = { Text("Passwort wiederholen") },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(35.dp))


                ClickButton(
                    text = "Registrieren",
                    modifier = Modifier
                        .padding(horizontal = 120.dp)
                        .fillMaxWidth(),
                    onClick = {
                        val user = User(
                            name = userNameState.value.text,
                            email = emailState.value.text,
                            password = passwordState.value.text
                        )

                        when {
                            // Validierungen

                            userNameState.value.text.isBlank() ->
                                registrationError = RegistrationError.EMPTY_USERNAME
                            emailState.value.text.isBlank() ->
                                registrationError = RegistrationError.EMPTY_EMAIL
                            passwordState.value.text.isBlank() ->
                                registrationError = RegistrationError.EMPTY_PASSWORD
                            passwordState.value.text != repeatPasswordState.value.text ->
                                registrationError = RegistrationError.PASSWORD_MISMATCH
                            UsersRepository.userData.any { it.email == user.email } ->
                                registrationError = RegistrationError.EMAIL_EXISTS
                            UsersRepository.userData.any { it.name == user.name } ->
                                registrationError = RegistrationError.USERNAME_EXISTS
                            !isValidEmail(user.email) ->
                                registrationError = RegistrationError.INVALID_EMAIL
                            else -> {
                                // Registrierung erfolgreich
                                UsersRepository.userData = UsersRepository.userData.apply { add(user) }
                                AuthManager.login(user)
                                navController.navigate("ProfileScreen1/${user.name}") {
                                    popUpTo(navController.graph.startDestinationId) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))

                ClickButton(
                    text = "Abbrechen",
                    onClick = {navController.navigate("LoginScreen")},
                    modifier = Modifier
                        .padding(horizontal = 120.dp)
                        .fillMaxWidth()
                )
            }
            //MenuBar(navController)
        }

    }

}







// Hilfsfunktion für E-Mail-Validierung
private fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

// Fehlertypen
enum class RegistrationError(val message: String) {
    EMPTY_USERNAME("Bitte Benutzernamen eingeben"),
    EMPTY_EMAIL("Bitte E-Mail-Adresse eingeben"),
    EMPTY_PASSWORD("Bitte Passwort eingeben"),
    PASSWORD_MISMATCH("Passwörter stimmen nicht überein"),
    EMAIL_EXISTS("E-Mail-Adresse bereits registriert"),
    USERNAME_EXISTS("Benutzername bereits vergeben"),
    INVALID_EMAIL("Ungültiges E-Mail-Format")
}