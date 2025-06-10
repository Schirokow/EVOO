package com.example.evoo

import android.app.Application
import android.content.Context
import com.example.evoo.users.UsersRepository

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

//Wichtige Punkte:
//Application-Klasse wird vor der ersten Activity instanziiert
//UsersRepository.userData initialisiert das Repository beim App-Start
//App-Klasse (Application-Kontext)
//Zweck: Stellt globalen App-Kontext bereit.
//onCreate: Wird beim App-Start aufgerufen, initialisiert den Repository.