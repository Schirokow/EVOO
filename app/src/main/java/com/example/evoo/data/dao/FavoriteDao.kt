package com.example.evoo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.evoo.data.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorites WHERE festivalId = :festivalId")
    suspend fun deleteFavorite(festivalId: Int)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query("SELECT EXISTS (SELECT 1 FROM favorites WHERE festivalId = :festivalId)")
    suspend fun isFavorite(festivalId: Int): Boolean

    @Query("DELETE FROM favorites")
    suspend fun deleteAllFavorites()
}

//Erklärung:
//insertFavorite: Fügt eine festivalId zur favorites-Tabelle hinzu.
//deleteFavorite: Entfernt eine festivalId aus der favorites-Tabelle.
//getAllFavorites: Gibt alle favorisierten festivalIds als Flow zurück.
//isFavorite: Überprüft, ob eine festivalId in der favorites-Tabelle existiert.