package com.example.evoo.data
import android.content.Context
import androidx.room.Room
import com.example.evoo.data.dao.FavoriteDao


object DatabaseProvider {

    @Volatile
    private var FAVORITE_INSTANCE: FavoriteDatabase? = null

    fun getFavoriteDatabase(context: Context): FavoriteDatabase {
        return FAVORITE_INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                FavoriteDatabase::class.java,
                "favorite_database"
            )
                .build()
            FAVORITE_INSTANCE = instance
            instance
        }
    }



    fun provideFavoriteDao(context: Context): FavoriteDao {
        return getFavoriteDatabase(context).favoriteDao()
    }
}

//Erklärung:
//Room.databaseBuilder: Erstellt die Datenbank mit dem Namen "festival_database".
//@Volatile und synchronized: Stellen sicher, dass die Datenbank thread-sicher initialisiert wird.
//Die Datenbank wird nur einmal erstellt und wiederverwendet.
//Methode getFavoriteDatabase für die FavoriteDatabase.
//provideFavoriteDao für einfachen Zugriff auf FavoriteDao.
//Zwei separate Instanzen (FESTIVAL_INSTANCE und FAVORITE_INSTANCE), um beide Datenbanken unabhängig zu verwalten.