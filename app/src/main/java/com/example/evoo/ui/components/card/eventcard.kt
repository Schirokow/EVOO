package com.example.evoo.ui.components.card


import androidx.compose.ui.Alignment
import com.example.evoo.model.Event

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
<<<<<<< HEAD
import androidx.compose.foundation.layout.aspectRatio
=======

>>>>>>> BottomBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite

import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.evoo.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue




@Preview //(showBackground = true)
@Composable
fun PreviewEventCard(){
    val event = Event(
        title = "Smooth Sound",
        date = "05.05.2025, 20:25 Uhr" ,
        description = "Free Entry - All Welcome",
        imageResId = R.drawable.festival1
    )
    EventCard(
        event = event,
        onClick = {} ,
        isLarge = true
    )
}

@Composable
fun EventCard(
    event : Event,
    modifier : Modifier = Modifier,
    onClick: () -> Unit,
    isLarge : Boolean = false,
){
<<<<<<< HEAD
var isLiked by remember { mutableStateOf(false) }
var isFavorited by remember { mutableStateOf (false)}
=======
>>>>>>> 2240d7528492ebd40603dc5d2e31a55f41d8cc88

val cardSize = if (isLarge) 200.dp else 100.dp
val imageToUse = event.imageResId ?: R.drawable.festival1

        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = modifier
                .size(cardSize)
                //.fillMaxWidth()
                //.aspectRatio(1f)
                .clickable(onClick = onClick)
        ){
            Box{
                Image(
                    painter = painterResource(id = imageToUse),
                    contentDescription = event.title,
                    contentScale = ContentScale.Crop,
                    //contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ){
                    Icon(
                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (isLiked) Color.Red else Color.White,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable{}
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Icon(
                        imageVector = if (isFavorited) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = "Favorit",
                        tint = if (isFavorited) Color.Yellow else Color.White,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable{}
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.8f))
                        .align(Alignment.BottomStart)
                ){
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ){
                    Text(
                    text = event.title,
                    color = Color.White,
                    fontSize = if (isLarge) 14.sp else 10.sp
                    )
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = event.date ?: "No Date",
                            color = Color.White,
                            fontSize = if (isLarge) 12.sp else 8.sp
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