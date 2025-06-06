package com.example.evoo.business

import android.app.Application
import android.content.Context
import com.example.evoo.data.UsersRepository

class App : Application() {
    companion object {
        // Globaler App-Kontext
        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext // Initialisiert den Kontext
        // Initialisiere UsersRepository
        UsersRepository.userData // Trigger für Repository-Initialisierung
    }
}