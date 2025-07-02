package com.example.evoo.data
import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile
    private var INSTANCE: FestivalDatabase? = null

    fun getDatabase(context: Context): FestivalDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                FestivalDatabase::class.java,
                "festival_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}

//Erklärung:
//Room.databaseBuilder: Erstellt die Datenbank mit dem Namen "festival_database".
//@Volatile und synchronized: Stellen sicher, dass die Datenbank thread-sicher initialisiert wird.
//Die Datenbank wird nur einmal erstellt und wiederverwendet.