package com.example.evoo.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object AuthManager {
    // Reaktive State-Variable für aktuellen Benutzer
    var currentUser: User? by mutableStateOf(null)
        private set

    // Setzt currentUser und löst UI-Update aus
    fun login(user: User) {
        currentUser = user // Compose registriert diese Änderung automatisch
    }

    // Löscht aktuellen Benutzer
    fun logout() {
        currentUser = null
    }
}

//AuthManager (Authentifizierungszustand)
//Zweck: Verwaltet den globalen Anmeldezustand.
//private set: Externe Klassen können currentUser nicht direkt setzen.

//Funktionsablauf bei Login:
//login(user) wird aufgerufen
//currentUser-Änderung wird an alle Compose-Components propagiert
//UI-Elemente, die currentUser beobachten, werden neu gezeichnet