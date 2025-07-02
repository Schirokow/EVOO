package com.example.evoo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.evoo.data.FestivalData
import kotlinx.coroutines.flow.Flow

@Dao
interface FestivalDao {
    @Insert
    suspend fun insert(festival: FestivalData) // Fügt ein Festival ein

    @Query("SELECT * FROM festivals")
    fun getAllFestivals(): Flow<List<FestivalData>> // Gibt alle Festivals als Flow zurück

    @Query("SELECT * FROM festivals WHERE id = :festivalId")
    suspend fun getFestivalById(festivalId: Int): FestivalData? // Gibt ein Festival nach ID zurück
}

// Erklärung:
//@Dao: Markiert die Schnittstelle als Data Access Object.
//@Insert: Fügt ein FestivalData-Objekt in die Tabelle ein.
//@Query("SELECT * FROM festivals"): Ruft alle Festivals ab. Flow ermöglicht reaktive Updates, wenn sich die Daten ändern.
//@Query("SELECT * FROM festivals WHERE id = :festivalId"): Ruft ein bestimmtes Festival anhand seiner ID ab.