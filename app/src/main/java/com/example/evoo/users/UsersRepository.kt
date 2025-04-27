package com.example.evoo.users

import com.example.evoo.R


data class User(
    val name: String,
    val email: String,
    val password: String,
    val profilePicture: Int = R.drawable.default_avatar
)

object UsersRepository {
    var userData: MutableList<User>
        get() = PreferencesManager.loadUsers().toMutableList()
        set(value) = PreferencesManager.saveUsers(value)

    init {
        // Initiale Testdaten nur beim ersten Start hinzufügen
        if (userData.isEmpty()) {
            userData = mutableListOf(
                User("Alex", "alex@test.de", "1234"),
                User("Sergei", "sergei@test.de", "12345"),
                User("Anna", "anna@test.de", "123456", R.drawable.avatar2),
                User("Max", "max@test.de", "1234567")
            )
        }
    }
}
