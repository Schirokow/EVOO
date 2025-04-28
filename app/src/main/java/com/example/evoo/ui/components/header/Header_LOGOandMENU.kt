package com.example.evoo.ui.components.header

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.evoo.R
import com.example.evoo.ui.screens.ForegroundColor


@Preview(showBackground = true)
@Composable
fun HeaderBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.Transparent),
    contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),

            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Evoo Logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(start = 32.dp)
            )

            IconButton(onClick = {},
                modifier = Modifier
                    .size(100.dp)) {
                Icon(
                    modifier = Modifier
                        .size(40.dp),
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menü" ,
                    tint = ForegroundColor

                )


            }
        }
    }
    }
