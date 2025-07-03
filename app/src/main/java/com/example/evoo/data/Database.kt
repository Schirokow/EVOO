package com.example.evoo.data
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.evoo.data.dao.FestivalDao

@Database(entities = [FestivalData::class], version = 1, exportSchema = false)
abstract class FestivalDatabase : RoomDatabase() {
    abstract fun festivalDao(): FestivalDao
}

// Erklärung:
//@Database(entities = [FestivalData::class], version = 1): Definiert die Datenbank mit der FestivalData-Entity und einer Version (für spätere Migrationen).
//abstract fun festivalDao(): FestivalDao: Stellt das DAO bereit, um auf die Datenbank zuzugreifen.
//Die Klasse muss abstrakt sein und von RoomDatabase erben.
