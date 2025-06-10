package com.example.evoo.model

import com.example.evoo.R


data class Event(
    val title: String,
    val date: String?,
    val description: String,
    val imageResId: Int? = null
)

val sampleEvents = listOf(
    Event("Smooth Sound", "05.05.2025, 20:25 Uhr", "Free Entry - All Welcome",R.drawable.festival1),
    Event("Jazz Night", "10.05.2025, 19:00 Uhr", "Enjoy some smooth jazz with free drinks.",R.drawable.festival2),
    Event("Rock Festival", "12.05.2025, 16:30 Uhr", "The best rock bands are playing live!",R.drawable.festival3),
    Event("Tech Meetup", "15.05.2025, 18:00 Uhr", "Join us for tech talks and networking.",R.drawable.festival6),
    Event("Comedy Show", "18.05.2025, 20:00 Uhr", "Get ready to laugh all night!",R.drawable.festival2),
    Event("Classical Concert", "20.05.2025, 18:00 Uhr", "An evening of classical music.",R.drawable.festival1),
    Event("Food Truck Festival", "22.05.2025, 12:00 Uhr", "Taste food from various food trucks.",R.drawable.festival5),
    Event("Gaming Expo", "25.05.2025, 10:00 Uhr", "A great place for gamers to meet.",R.drawable.festival6),
    Event("Art Exhibition", "30.05.2025, 14:00 Uhr", "Explore new art from talented artists.",R.drawable.festival2),
    Event("Film Screening", "02.06.2025, 19:30 Uhr", "Watch classic films with friends.",R.drawable.festival3),
    Event("Fitness Challenge", "05.06.2025, 09:00 Uhr", "Join the fitness event for all levels.",R.drawable.festival4),
    Event("Cooking Workshop", "10.06.2025, 17:00 Uhr", "Learn new recipes and cooking techniques.",R.drawable.festival1),
    Event("Book Launch", "15.06.2025, 20:00 Uhr", "Meet the author and discover new books.",R.drawable.festival2),
    Event("Dance Party", "20.06.2025, 21:00 Uhr", "Get ready to dance all night!",R.drawable.festival4),
    Event("Open Mic Night", "25.06.2025, 19:00 Uhr", "Share your talent with an open mic.",R.drawable.festival3),
    Event("Science Fair", "30.06.2025, 10:00 Uhr", "Explore the latest scientific innovations.",R.drawable.festival5),
    Event("Charity Run", "05.07.2025, 08:00 Uhr", "Join us for a charity run for a good cause.",R.drawable.festival2),
    Event("Photo Walk", "10.07.2025, 07:00 Uhr", "Capture beautiful moments during a scenic walk.",R.drawable.festival4),
    Event("Poetry Slam", "15.07.2025, 18:00 Uhr", "An evening of spoken word and poetry.",R.drawable.festival6),
    Event("Wine Tasting", "20.07.2025, 17:00 Uhr", "Taste and explore different wines.",R.drawable.festival3)
)
val sampleImages = listOf(
    R.drawable.festival1,
    R.drawable.festival2,
    R.drawable.festival3,
    R.drawable.festival4,
    R.drawable.festival5,
    R.drawable.festival6
)