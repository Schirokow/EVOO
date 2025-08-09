package com.example.evoo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.evoo.data.FavoriteRepository
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log
import com.example.evoo.business.usecases.GetEventByIdUseCase
import com.example.evoo.business.usecases.GetEventsUseCase
import com.example.evoo.data.TicketmasterEvent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

class ContentDetailViewModel(
    private val favoriteRepository: FavoriteRepository,
    private val eventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val getEventByIdUseCase: GetEventByIdUseCase = GetEventByIdUseCase()

    private val _event = MutableStateFlow<TicketmasterEvent?>(null)
    val event: StateFlow<TicketmasterEvent?> = _event.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()


    fun loadEvent(id: String) {
        viewModelScope.launch {
            try {
                // Lade das Event direkt über die API-ID
                val eventData = getEventByIdUseCase.getEventByIdFlow(id).firstOrNull()
                _event.value = eventData
            } catch (e: Exception) {
                Log.e("ContentDetailViewModel", "Error loading event: ${e.message}")
                _event.value = null
            }
        }
    }

    fun toggleFavorite(event: TicketmasterEvent) {
        viewModelScope.launch {
            try {
                if (favoriteRepository.isFavorite(event.id)) {
                    favoriteRepository.removeFavorite(event.id)
                    _isFavorite.value = false
                    Log.i("ContentDetailViewModel", "Removed favorite: ${event.id}")
                } else {
                    favoriteRepository.addFavorite(event)
                    _isFavorite.value = true
                    Log.i("ContentDetailViewModel", "Added favorite: ${event.id}")
                }
            } catch (e: Exception) {
                Log.e("ContentDetailViewModel", "Error toggling favorite: ${e.message}")
            }
        }
    }
}