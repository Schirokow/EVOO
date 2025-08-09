package com.example.evoo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evoo.data.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log
import com.example.evoo.data.Favorite
import com.example.evoo.data.TicketmasterEvent

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    private val _favoriteEvents = MutableStateFlow<List<Favorite>>(emptyList())
    val favoriteEvents: StateFlow<List<Favorite>> = _favoriteEvents.asStateFlow()

    init {
        viewModelScope.launch {
            favoriteRepository.getFavoriteEvents().collect { favorites ->
                _favoriteEvents.value = favorites
            }
        }
    }

    fun toggleFavorite(favoriteEvent: Favorite) {
        viewModelScope.launch {
            try {
                if (favoriteRepository.isFavorite(favoriteEvent.eventId)) {
                    favoriteRepository.removeFavorite(favoriteEvent.eventId)
                    Log.i("FavoriteViewModel", "Removed favorite: ${favoriteEvent.eventId}")
                }
            } catch (e: Exception) {
                Log.e("FavoriteViewModel", "Error toggling favorite: ${e.message}")
            }
        }
    }

    fun deleteAllFavorites() {
        viewModelScope.launch {
            try {
                favoriteRepository.deleteAllFavorites()
                _favoriteEvents.value = emptyList() // UI sofort aktualisieren
                Log.i("FavoriteViewModel", "All favorites deleted")
            } catch (e: Exception) {
                Log.e("FavoriteViewModel", "Error deleting all favorites: ${e.message}")
            }
        }
    }
}