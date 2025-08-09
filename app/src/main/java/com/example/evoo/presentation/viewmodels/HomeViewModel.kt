package com.example.evoo.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evoo.business.usecases.GetEventsUseCase
import com.example.evoo.data.FavoriteRepository
import com.example.evoo.data.TicketmasterEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class HomeViewModel(
    val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private val getEventsUseCase: GetEventsUseCase = GetEventsUseCase()

    private val _eventsData = MutableStateFlow<List<TicketmasterEvent>>(emptyList())
    val eventsData: StateFlow<List<TicketmasterEvent>> = _eventsData.asStateFlow()


    fun loadAllEvents(city: String) {
        viewModelScope.launch {
            getEventsUseCase.getEventsFlow(city).collect { evetnts ->
                _eventsData.value = evetnts
            }
        }
    }

    fun deleteAllEvents() {
        viewModelScope.launch {
            try {
//                festivalRepository.deleteAllFestivals()
                _eventsData.value = emptyList()
                Log.i("HomeViewModel", "All festivals deleted")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error deleting festivals: ${e.message}")
            }
        }
    }

    fun toggleFavorite(event: TicketmasterEvent) {
        viewModelScope.launch {
            try {
                if (favoriteRepository.isFavorite(event.id)) {
                    favoriteRepository.removeFavorite(event.id)
                    Log.i("HomeViewModel", "Removed favorite: ${event.id}")
                } else {
                    favoriteRepository.addFavorite(event)
                    Log.i("HomeViewModel", "Added favorite: ${event.id}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error toggling favorite: ${e.message}")
            }
        }
    }
}



