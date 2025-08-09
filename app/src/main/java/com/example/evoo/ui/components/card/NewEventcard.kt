package com.example.evoo.ui.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evoo.R
import com.example.evoo.data.Event
import com.example.evoo.data.FestivalData
import com.example.evoo.data.TicketmasterDates
import com.example.evoo.data.TicketmasterImage
import coil.compose.AsyncImage // Import für die AsyncImage-Komponente


@Preview //(showBackground = true)
@Composable
fun PreviewNewEventCard(){

//    NewEventCard(
//        image = R.drawable.festival3,
//        title = "Festival",
//        datum = "04.07.2025",
//        onClick = {} ,
//        isLarge = true
//    )
}

@Composable
fun NewEventCard(
    image: List<TicketmasterImage>? = null,
    title: String,
    datum: String? = null,
    modifier : Modifier = Modifier,
    onClick: () -> Unit,
    isLarge : Boolean = false,
    textIsLarge: Boolean = false
){
    val cardSize = if (isLarge) 200.dp else 100.dp
    val imageToUse = image ?: R.drawable.festival1
    val imageUrl = image?.firstOrNull()?.url
    val fallbackImage = R.drawable.festival1 // Dein lokales Fallback-Bild

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        modifier = modifier
            .size(cardSize)
            //.fillMaxWidth()
            //.aspectRatio(1f)
            .clickable(onClick = onClick)
    ){
        Box{

            // Verwende AsyncImage, um das Bild von der URL zu laden
            if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // Zeige das Fallback-Bild an, wenn keine URL vorhanden ist
                Image(
                    painter = painterResource(id = fallbackImage),
                    contentDescription = title,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }





//            Image(
//                painter = painterResource(id = imageToUse as Int),
//                contentDescription = title,
//                contentScale = ContentScale.FillBounds,
//                modifier = Modifier.fillMaxSize()
//            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .align(Alignment.BottomStart)
            ){
                Column(
                    modifier = Modifier.padding(8.dp)
                ){
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = if (textIsLarge) 34.sp else 10.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = datum ?: "No Date",
                        color = Color.White,
                        fontSize = if (textIsLarge) 22.sp else 8.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    //Text(
                    //  text = event.description,
                    //color = Color.White,
                    //fontSize = if (isLarge) 12.sp else 8.sp
                    //  )

                }

            }
        }
    }
}