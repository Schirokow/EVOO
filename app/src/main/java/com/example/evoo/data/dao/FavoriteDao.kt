package com.example.evoo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.evoo.data.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // Fügt einen Favoriten hinzu oder ersetzt ihn bei ID-Konflikt
    suspend fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorites WHERE eventId = :eventId")
    suspend fun deleteFavorite(eventId: String)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query("SELECT EXISTS (SELECT 1 FROM favorites WHERE eventId = :eventId)")
    suspend fun isFavorite(eventId: String): Boolean

    @Query("DELETE FROM favorites")
    suspend fun deleteAllFavorites()
}

//Erklärung:
//insertFavorite: Fügt eine festivalId zur favorites-Tabelle hinzu.
//deleteFavorite: Entfernt eine festivalId aus der favorites-Tabelle.
//getAllFavorites: Gibt alle favorisierten festivalIds als Flow zurück.
//isFavorite: Überprüft, ob eine festivalId in der favorites-Tabelle existiert.