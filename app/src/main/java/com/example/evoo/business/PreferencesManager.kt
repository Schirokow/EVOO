package com.example.evoo.business

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.evoo.data.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//object PreferencesManager {
//    // Name der SharedPreferences-Datei
//    private const val PREFS_NAME = "user_prefs"
//    // Schlüssel für die gespeicherten Benutzerdaten
//    private const val KEY_USERS = "users"
//
//    // SharedPreferences-Instanz mit Jetpack Compose State-Integration
//    private var prefs: SharedPreferences by mutableStateOf(
//        App.Companion.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//    )
//
//    // Serialisiert Benutzerliste zu JSON und speichert sie
//    fun saveUsers(users: List<User>) {
//        val json = Gson().toJson(users) // Konvertiert Liste zu JSON-String
//        prefs.edit() // Startet Editor für SharedPreferences
//            .putString(KEY_USERS, json) // Speichert unter KEY_USERS
//            .apply() // Asynchrones Speichern (kein Rückgabewert)
//    }
//
//    // Lädt Benutzerliste aus JSON
//    fun loadUsers(): List<User> {
//        val json = prefs.getString(KEY_USERS, null) // Holt gespeicherten JSON-String
//        return if (json != null) {
//            val type = object : TypeToken<List<User>>() {}.type // TypToken erforderlich, um generische Typen zu handhaben
//            Gson().fromJson(json, type) ?: emptyList() // Deserialisierung, der gespeicherte JSON-String wird zurück in eine List<User> konvertiert.
//        } else {
//            emptyList() // Fallback für leere Liste
//        }
//    }
//}