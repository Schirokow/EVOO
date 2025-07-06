package com.example.evoo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.evoo.data.dao.FavoriteDao

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}

//Erklärung:
//@Database(entities = [Favorite::class], version = 1) definiert die Datenbank mit der favorites-Tabelle.
//favoriteDao() stellt das DAO für die favorites-Tabelle bereit.