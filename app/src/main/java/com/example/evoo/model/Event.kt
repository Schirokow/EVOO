package com.example.evoo.model



data class Event(
    val title: String,
    val date: String?,
    val description: String
)

val sampleEvents = listOf(
    Event("Smooth Sound", "05.05.2025, 20:25 Uhr", "Free Entry - All Welcome"),
    Event("Jazz Night", "10.05.2025, 19:00 Uhr", "Enjoy some smooth jazz with free drinks."),
    Event("Rock Festival", "12.05.2025, 16:30 Uhr", "The best rock bands are playing live!"),
    Event("Tech Meetup", "15.05.2025, 18:00 Uhr", "Join us for tech talks and networking."),
    Event("Comedy Show", "18.05.2025, 20:00 Uhr", "Get ready to laugh all night!"),
    Event("Classical Concert", "20.05.2025, 18:00 Uhr", "An evening of classical music."),
    Event("Food Truck Festival", "22.05.2025, 12:00 Uhr", "Taste food from various food trucks."),
    Event("Gaming Expo", "25.05.2025, 10:00 Uhr", "A great place for gamers to meet."),
    Event("Art Exhibition", "30.05.2025, 14:00 Uhr", "Explore new art from talented artists."),
    Event("Film Screening", "02.06.2025, 19:30 Uhr", "Watch classic films with friends."),
    Event("Fitness Challenge", "05.06.2025, 09:00 Uhr", "Join the fitness event for all levels."),
    Event("Cooking Workshop", "10.06.2025, 17:00 Uhr", "Learn new recipes and cooking techniques."),
    Event("Book Launch", "15.06.2025, 20:00 Uhr", "Meet the author and discover new books."),
    Event("Dance Party", "20.06.2025, 21:00 Uhr", "Get ready to dance all night!"),
    Event("Open Mic Night", "25.06.2025, 19:00 Uhr", "Share your talent with an open mic."),
    Event("Science Fair", "30.06.2025, 10:00 Uhr", "Explore the latest scientific innovations."),
    Event("Charity Run", "05.07.2025, 08:00 Uhr", "Join us for a charity run for a good cause."),
    Event("Photo Walk", "10.07.2025, 07:00 Uhr", "Capture beautiful moments during a scenic walk."),
    Event("Poetry Slam", "15.07.2025, 18:00 Uhr", "An evening of spoken word and poetry."),
    Event("Wine Tasting", "20.07.2025, 17:00 Uhr", "Taste and explore different wines.")
)