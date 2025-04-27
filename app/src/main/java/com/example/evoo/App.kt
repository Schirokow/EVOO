package com.example.evoo

import android.app.Application
import android.content.Context
import com.example.evoo.users.UsersRepository

class App : Application() {
    companion object {
        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        // Initialisiere UsersRepository
        UsersRepository.userData
    }
}