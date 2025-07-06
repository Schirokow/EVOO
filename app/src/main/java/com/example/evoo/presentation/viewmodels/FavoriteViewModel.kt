package com.example.evoo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evoo.data.FestivalData
import com.example.evoo.data.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log
import com.example.evoo.data.Favorite

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    private val _favoriteFestivals = MutableStateFlow<List<Favorite>>(emptyList())
    val favoriteFestivals: StateFlow<List<Favorite>> = _favoriteFestivals.asStateFlow()

    init {
        viewModelScope.launch {
            favoriteRepository.getFavoriteFestivals().collect { favorites ->
                _favoriteFestivals.value = favorites
            }
        }
    }

    fun toggleFavorite(festival: FestivalData) {
        viewModelScope.launch {
            try {
                if (favoriteRepository.isFavorite(festival.id)) {
                    favoriteRepository.removeFavorite(festival.id)
                    Log.i("FavoriteViewModel", "Removed favorite: ${festival.id}")
                } else {
                    favoriteRepository.addFavorite(festival)
                    Log.i("FavoriteViewModel", "Added favorite: ${festival.id}")
                }
            } catch (e: Exception) {
                Log.e("FavoriteViewModel", "Error toggling favorite: ${e.message}")
            }
        }
    }

//    fun removeFavorite(festivalId: Int) {
//        viewModelScope.launch {
//            try {
//                favoriteRepository.removeFavorite(festivalId)
//                Log.i("FavoriteViewModel", "Removed favorite: $festivalId")
//            } catch (e: Exception) {
//                Log.e("FavoriteViewModel", "Error removing favorite: ${e.message}")
//            }
//        }
//    }

    fun deleteAllFavorites() {
        viewModelScope.launch {
            try {
                favoriteRepository.deleteAllFavorites()
                _favoriteFestivals.value = emptyList() // UI sofort aktualisieren
                Log.i("FavoriteViewModel", "All favorites deleted")
            } catch (e: Exception) {
                Log.e("FavoriteViewModel", "Error deleting all favorites: ${e.message}")
            }
        }
    }
}