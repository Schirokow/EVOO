package com.example.evoo.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object AuthManager {
    var currentUser: User? by mutableStateOf(null)
        private set

    fun login(user: User) {
        currentUser = user
    }

    fun logout() {
        currentUser = null
    }
}