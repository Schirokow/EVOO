package com.example.evoo.business

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.evoo.data.User

//object AuthManager {
//    // Reaktive State-Variable für aktuellen Benutzer
//    var currentUser: User? by mutableStateOf(null)
//        private set
//
//    // Setzt currentUser und löst UI-Update aus
//    fun login(user: User) {
//        currentUser = user // Compose registriert diese Änderung automatisch
//    }
//
//    // Löscht aktuellen Benutzer
//    fun logout() {
//        currentUser = null
//    }
//}