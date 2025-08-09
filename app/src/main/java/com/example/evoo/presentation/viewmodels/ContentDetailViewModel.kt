package com.example.evoo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.evoo.business.usecases.GetFestivalsUseCase
import com.example.evoo.data.FestivalData
import com.example.evoo.data.FestivalRepository
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
    private val festivalRepository: FestivalRepository,
    private val favoriteRepository: FavoriteRepository,
    private val eventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val getEventByIdUseCase: GetEventByIdUseCase = GetEventByIdUseCase()
    private val _festival = MutableStateFlow<FestivalData?>(null)
    val festival: StateFlow<FestivalData?> = _festival.asStateFlow()

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

                // Die Favoriten-Funktionalität musst du an das neue Event-Modell anpassen
                // Das ist ein komplexeres Thema, also belassen wir es vorerst bei den Festivals
                // oder passen es entsprechend an, falls du das benötigst.
                // _isFavorite.value = favoriteRepository.isFavorite(id) // Passend für TicketmasterEvent
            } catch (e: Exception) {
                Log.e("ContentDetailViewModel", "Error loading event: ${e.message}")
                _event.value = null
            }
        }
    }

    // Die toggleFavorite-Funktion würde nun ein TicketmasterEvent-Objekt erwarten
    // Funktioniert so nicht mehr direkt mit FestivalData
    // Da deine DB noch auf FestivalData basiert, musst du hier eine Anpassung vornehmen.
    // Für diesen Schritt ignorieren wir die Favorite-Funktion

//    fun loadFestival(id: Int) {
//        viewModelScope.launch {
//            _festival.value = festivalRepository.getFestivalById(id)
//            _isFavorite.value = favoriteRepository.isFavorite(id)
//        }
//    }
//fun loadFestival(id: String) {
//    viewModelScope.launch {
//        try {
//            // Versuche zuerst, das Festival aus der festivals-Tabelle zu laden
//            var festivalData = festivalRepository.getFestivalById(id)
//            if (festivalData != null) {
//                Log.d("ContentDetailViewModel", "Festival found in festivals table: ${festivalData.title}")
//            } else {
//                // Fallback: Lade aus der favorites-Tabelle
//                val favorite = favoriteRepository.getFavoriteFestivals().first().find { it.festivalId == id }
//                if (favorite != null) {
//                    festivalData = FestivalData(
//                        id = favorite.festivalId,
//                        imageId = favorite.imageId,
//                        title = favorite.title,
//                        description = favorite.description,
//                        datum = favorite.datum,
//                        location = favorite.location
//                    )
//                    Log.d("ContentDetailViewModel", "Festival found in favorites table: ${festivalData.title}")
//                } else {
//                    Log.w("ContentDetailViewModel", "Festival not found in either table for id: $id")
//                }
//            }
//            _festival.value = festivalData
//            _isFavorite.value = favoriteRepository.isFavorite(id)
//        } catch (e: Exception) {
//            Log.e("ContentDetailViewModel", "Error loading festival: ${e.message}")
//            _festival.value = null
//        }
//    }
//}

    fun toggleFavorite(festival: FestivalData) {
        viewModelScope.launch {
            try {
                if (favoriteRepository.isFavorite(festival.id)) {
                    favoriteRepository.removeFavorite(festival.id)
                    _isFavorite.value = false
                    Log.i("ContentDetailViewModel", "Removed favorite: ${festival.id}")
                } else {
                    favoriteRepository.addFavorite(festival)
                    _isFavorite.value = true
                    Log.i("ContentDetailViewModel", "Added favorite: ${festival.id}")
                }
            } catch (e: Exception) {
                Log.e("ContentDetailViewModel", "Error toggling favorite: ${e.message}")
            }
        }
    }
}