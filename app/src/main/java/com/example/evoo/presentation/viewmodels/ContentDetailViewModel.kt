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

//class ContentDetailViewModel: ViewModel() {
//    private val getFestivalsUseCase: GetFestivalsUseCase = GetFestivalsUseCase()
//    private val _festival = MutableStateFlow<FestivalData?>(null)
//    val festival: StateFlow<FestivalData?> = _festival.asStateFlow()
//
//    fun loadFestival(id: Int) {
//        viewModelScope.launch {
//            getFestivalsUseCase.getFestivalByIdFlow(id).collect { festivalData ->
//                _festival.value = festivalData
//            }
//        }
//    }
//}

//class ContentDetailViewModel(private val repository: FestivalRepository) : ViewModel() {
//    private val _festival = MutableStateFlow<FestivalData?>(null)
//    val festival: StateFlow<FestivalData?> = _festival.asStateFlow()
//
//    fun loadFestival(id: Int) {
//        viewModelScope.launch {
//            _festival.value = repository.getFestivalById(id)
//        }
//    }
//}

class ContentDetailViewModel(
    private val festivalRepository: FestivalRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private val _festival = MutableStateFlow<FestivalData?>(null)
    val festival: StateFlow<FestivalData?> = _festival.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    fun loadFestival(id: Int) {
        viewModelScope.launch {
            _festival.value = festivalRepository.getFestivalById(id)
            _isFavorite.value = favoriteRepository.isFavorite(id)
        }
    }

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