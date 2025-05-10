package com.example.evoo.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextAlign
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
import com.example.evoo.users.UsersRepository.userData

private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(navController: NavController) {
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
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Zurück",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp, bottom = 24.dp)
                        .size(34.dp)
                        .clickable {
                            Log.d(TAG, "Navigating to registration screen")
                            navController.navigate("RegistrationScreen")
                        }
                )

                Text(
                    text = "Neues Konto",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier
                        .clickable{
                            Log.d(TAG, "Navigating to registration screen")
                            navController.navigate("RegistrationScreen")
                        }
                )
            }


            // State-Management mit Jetpack Compose
            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val passwordState = remember { mutableStateOf(TextFieldValue()) }

            var loginError by remember { mutableStateOf(false) }
            if (loginError) {
                Log.w(TAG, "Showing login error dialog")
                AlertDialog(
                    onDismissRequest = {
                        Log.d(TAG, "Dismissed login error dialog")
                        // Schließt den Dialog wenn der Benutzer außerhalb tippt
                        loginError = false
                    },
                    title = {
                        Text(
                            text = "Fehler bei der Anmeldung",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    text = {
                        Text("E-Mail oder Passwort ist falsch!")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                Log.d(TAG, "User acknowledged login error")
                                loginError = false
                            }
                        ) {
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


                Image(
                    painter = painterResource(id = R.drawable.logo_anye),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .padding(top = 120.dp)
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

                Spacer(modifier = Modifier.height(80.dp))

                TextField(
                    value = emailState.value,
                    onValueChange = { newText -> emailState.value = newText },
                    label = { Text("E-Mail oder Benutzername") },
                    placeholder = { Text("E-Mail/Benutzername") },
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
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Passwort vergessen?",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 150.dp)
                        .clickable{/* TODO */}
                )
                Spacer(modifier = Modifier.height(64.dp))

                ClickButton(
                    text = "Anmelden",
                    onClick = {
                        val email = emailState.value.text
                        val password = passwordState.value.text

                        Log.d(TAG, "Login attempt started.")

                        val user = userData.find{
                            (it.email == email || it.name == email) && it.password == password
                        }

                        if (user != null) {
                            Log.i(TAG, "Login successful for user: ${user.name}")
                            loginError = false
                            // Eingabefelder leeren
                            emailState.value = TextFieldValue("")
                            passwordState.value = TextFieldValue("")

                            AuthManager.login(user).also {
                                Log.d(TAG, "AuthManager login state updated")
                            } // Benutzer setzen, Globaler Login

                            Log.d(TAG, "Navigating to profile: ${user.name}")
                            navController.navigate("ProfileScreen1/${user.name}") {
                                //Löscht den gesamten Back-Stack
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                //Verhindert Mehrfachinstanzen
                                launchSingleTop = true
                            }
                    } else{
                            Log.w(TAG, "Login failed for email: ${email.take(3)}... (invalid credentials)")
                            loginError = true // Zeigt Fehlerdialog
                    }
                    },
                    modifier = Modifier
                        .padding(horizontal = 120.dp)
                        .fillMaxWidth()
                )


            }
            MenuBar(navController)
        }

    }
}

//Anmeldeflow:
//
//Nutzer gibt Email/Passwort ein
//Button-Click löst Suche in userData aus
//
//Bei Erfolg:
//AuthManager aktualisiert globalen State
//Navigation zum Profil mit Username als Parameter
//Back-Stack wird gelöscht (kein Zurück zum Login)
//Bei Fehler: AlertDialog wird angezeigt