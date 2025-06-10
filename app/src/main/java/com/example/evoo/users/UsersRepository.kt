package com.example.evoo.users

import com.example.evoo.R


data class User(
    val name: String,
    val email: String,
    val password: String,
    val profilePicture: Int = R.drawable.default_avatar
)

object UsersRepository {
    // Custom Getter/Setter für Datenkapselung
    var userData: MutableList<User>
        get() = PreferencesManager.loadUsers().toMutableList() // Lädt aus Prefs
        set(value) = PreferencesManager.saveUsers(value) // Speichert bei Änderung

    init {
        // Wird beim ersten Zugriff auf UsersRepository ausgeführt
        if (userData.isEmpty()) {
            // Initiale Testdaten
            userData = mutableListOf(
                User("Alex", "alex@test.de", "1234"),
                User("Sergei", "sergei@test.de", "12345"),
                User("Anna", "anna@test.de", "123456", R.drawable.avatar2),
                User("Max", "max@test.de", "1234567")
            )
        }
    }
}

//Zweck: Zentrale Schnittstelle für Benutzerdaten.

//Getter/Setter: Automatische Synchronisation mit SharedPreferences.

//init-Block: Initialisiert Testbenutzer beim ersten App-Start.

//Initialisierungsflow:
//Beim ersten Aufruf von UsersRepository.userData
//loadUsers() wird ausgeführt
//Wenn leer → Testdaten werden hinzugefügt
//saveUsers() speichert Testdaten persistent