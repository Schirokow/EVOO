package com.example.evoo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.evoo.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.evoo.ui.components.buttons.ClickButton


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    loginScreen()
}




@Composable
fun loginScreen(){

    val emailState = remember {mutableStateOf(TextFieldValue())}
    val passwordState = remember {mutableStateOf(TextFieldValue())}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                    brush = Brush.verticalGradient(
                            colors = listOf(TopLightBlue,BottomDarkBlue)
                    )
),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.height(30.dp))

       Image(
           painter = painterResource(id = R.drawable.logo_anye),
           contentDescription = "App Logo",
           modifier = Modifier
               .size(120.dp)
               .align(Alignment.CenterHorizontally)
       )
        Text( "Event Up Your Life",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(100.dp))

        TextField(
            value = emailState.value,
            onValueChange = { newText -> emailState.value = newText },
            label = {Text("E-Mail oder Benutzername") },
            placeholder = {Text("E-Mail/Benutzername")},
            singleLine = true,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
        TextField(
            value = passwordState.value,
            onValueChange = { newText -> passwordState.value = newText },
            label = {Text("Passwort") },
            placeholder = {Text("Passwort")},
            singleLine = true,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(64.dp))

        ClickButton(
            text = "Anmelden",
            onClick = {},
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        ClickButton(
            text = "Neues Konto",
            onClick = {},
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth()
        )
            }}