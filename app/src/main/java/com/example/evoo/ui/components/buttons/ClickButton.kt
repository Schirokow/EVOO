package com.example.evoo.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable

fun ClickButton(
    text: String = "Press here",
    onClick: () -> Unit ={}
) {
    Button (
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .indication(interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current),
        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray , contentColor = Color.White)
        ,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 6.dp
        ),
        border = BorderStroke(2.dp, Brush.verticalGradient(
            colors = listOf(Color.Gray.copy(alpha = 0.3f), Color.Gray.copy(alpha = 0.9f)))),
        shape = RoundedCornerShape(12.dp)
    )
    {
        Text(text = text)
    }


}