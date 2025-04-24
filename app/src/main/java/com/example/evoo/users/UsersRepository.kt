package com.example.evoo.users

import com.example.evoo.R


data class User(
    val name: String,
    val email: String,
    val password: String,
    val profilePicture: Int = R.drawable.default_avatar
)

object UsersRepository{
    var userData = mutableListOf(
        User(
            name = "Alex",
            email = "alex@test.de",
            password = "1234"
        ),
        User(
            name = "Sergei",
            email = "sergei@test.de",
            password = "12345"
        ),
        User(
            name = "Anna",
            email = "anna@test.de",
            password = "123456",
            profilePicture = R.drawable.avatar2
        ),
        User(
            name = "Max",
            email = "max@test.de",
            password = "1234567"
        ),
    )
}