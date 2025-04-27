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
    private const val PREFS_NAME = "user_prefs"
    private const val KEY_USERS = "users"
    private var prefs: SharedPreferences by mutableStateOf(
        App.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    )

    fun saveUsers(users: List<User>) {
        val json = Gson().toJson(users)
        prefs.edit().putString(KEY_USERS, json).apply()
    }

    fun loadUsers(): List<User> {
        val json = prefs.getString(KEY_USERS, null)
        return if (json != null) {
            val type = object : TypeToken<List<User>>() {}.type
            Gson().fromJson(json, type) ?: emptyList()
        } else {
            emptyList()
        }
    }
}