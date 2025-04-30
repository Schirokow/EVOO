package com.example.evoo.users

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.evoo.App
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PreferencesManager {
    // Name der SharedPreferences-Datei
    private const val PREFS_NAME = "user_prefs"
    // Schlüssel für die gespeicherten Benutzerdaten
    private const val KEY_USERS = "users"

    // SharedPreferences-Instanz mit Jetpack Compose State-Integration
    private var prefs: SharedPreferences by mutableStateOf(
        App.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    )

    // Serialisiert Benutzerliste zu JSON und speichert sie
    fun saveUsers(users: List<User>) {
        val json = Gson().toJson(users) // Konvertiert Liste zu JSON-String
        prefs.edit() // Startet Editor für SharedPreferences
            .putString(KEY_USERS, json) // Speichert unter KEY_USERS
            .apply() // Asynchrones Speichern (kein Rückgabewert)
    }

    // Lädt Benutzerliste aus JSON
    fun loadUsers(): List<User> {
        val json = prefs.getString(KEY_USERS, null) // Holt gespeicherten JSON-String
        return if (json != null) {
            val type = object : TypeToken<List<User>>() {}.type // TypToken erforderlich, um generische Typen zu handhaben
            Gson().fromJson(json, type) ?: emptyList() // Deserialisierung, der gespeicherte JSON-String wird zurück in eine List<User> konvertiert.
        } else {
            emptyList() // Fallback für leere Liste
        }
    }
}

//PreferencesManager (Datenpersistenz)

//Zweck: Persistente Speicherung von Benutzerdaten.

//PREFS_NAME: Name der SharedPreferences-Datei.

//KEY_USERS: Schlüssel für die Benutzerliste.

//Gson: Serialisiert/Deserialisiert die User-Objekte in/aus JSON.

//mutableStateOf: Macht SharedPreferences-Änderungen für Compose-UIs beobachtbar.

//Key Points:
//apply() vs commit(): apply() ist asynchron und sicherer für UI-Thread

//TypeToken: Notwendig wegen Java-Type-Erasure (generische Typen)

//Konzept	Rolle im Code:
//JSON	Speicherformat für die User-Liste in SharedPreferences
//Gson	Bibliothek zur Konvertierung zwischen List<User> und JSON
//TypeToken	Hilft Gson, den generischen Typ List<User> zu erkennen