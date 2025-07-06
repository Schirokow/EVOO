package com.example.evoo.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evoo.business.usecases.GetFestivalsUseCase
import com.example.evoo.data.FavoriteRepository
import com.example.evoo.data.FestivalData
import com.example.evoo.data.FestivalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


//class HomeViewModel: ViewModel() {
//    private val getFestivalsUseCase: GetFestivalsUseCase = GetFestivalsUseCase()
//    // StateFlow für FestivalData hinzufügen
//    private val _festivalData = MutableStateFlow<List<FestivalData>>(emptyList())
//    val festivalData: StateFlow<List<FestivalData>> = _festivalData.asStateFlow()
//
//    // Kontinuierlicher Flow
//    init {
//        viewModelScope.launch {
//            getFestivalsUseCase.getFestivalsFlow().collect { festivals ->
//                _festivalData.value = festivals
//            }
//        }
//    }
//
//}

class HomeViewModel(
    private val festivalRepository: FestivalRepository,
    val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private val getFestivalsUseCase: GetFestivalsUseCase = GetFestivalsUseCase()
    private val _festivalData = MutableStateFlow<List<FestivalData>>(emptyList())
    val festivalData: StateFlow<List<FestivalData>> = _festivalData.asStateFlow()

    init {
        viewModelScope.launch {
            // Festivals laden
            festivalRepository.getFestivals().collect { festivals ->
                _festivalData.value = festivals
            }
        }

    }

    fun loadAllFestivals(){
        viewModelScope.launch {
            getFestivalsUseCase.getFestivalsFlow().collect { festivals ->
                festivalRepository.insertFestival(festivals)
            }
        }
    }

//    fun deleteAllFestivals() {
//        viewModelScope.launch {
//            festivalRepository.deleteAllFestivals()
//            _festivalData.value = emptyList() // UI sofort aktualisieren
//        }
//    }

    fun deleteAllFestivals() {
        viewModelScope.launch {
            try {
                festivalRepository.deleteAllFestivals()
                _festivalData.value = emptyList()
                Log.i("HomeViewModel", "All festivals deleted")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error deleting festivals: ${e.message}")
            }
        }
    }

    fun toggleFavorite(festival: FestivalData) {
        viewModelScope.launch {
            try {
                if (favoriteRepository.isFavorite(festival.id)) {
                    favoriteRepository.removeFavorite(festival.id)
                    Log.i("HomeViewModel", "Removed favorite: ${festival.id}")
                } else {
                    favoriteRepository.addFavorite(festival)
                    Log.i("HomeViewModel", "Added favorite: ${festival.id}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error toggling favorite: ${e.message}")
            }
        }
    }

//    init {
//        loadFestivalData()
//    }
//
//    fun loadFestivalData() {
//        viewModelScope.launch {
//            repository.getFestivals().collect { festivals ->
//                _festivalData.value = festivals
//            }
//        }
//    }

//    init {
//        viewModelScope.launch {
//            // Prüfen, ob die Datenbank leer ist
//            if (repository.getFestivals().first().isEmpty()) {
//                getFestivalsUseCase.getFestivalsFlow().collect { festivals ->
//                    repository.insertFestival(festivals)
//                }
                // Beispieldaten einfügen
//                val initialFestivals = getFestivalsUseCase.getFestivalsFlow()
                //                listOf(
//                    FestivalData(
//                        imageId = R.drawable.festival1,
//                        title = "Summer Festival",
//                        description = "Techno Festival",
//                        datum = "20 & 21 Juni 2025",
//                        location = "Am Strand"
//                    ),
//                    FestivalData(
//                        imageId = R.drawable.festival2,
//                        title = "Heaven & Hill Neukirchen",
//                        description = "Disco-Fest",
//                        datum = "20 & 21 Juni 2025",
//                        location = "Neukirchen-Vluyn"
//                    ),
//                    // Weitere Festivals hier hinzufügen...
//                    FestivalData(
//                        imageId = R.drawable.festival12,
//                        title = "Latin Airport Festival",
//                        description = "Latino Festival",
//                        datum = "05.Juli 2025",
//                        location = "Airport Nürnberg"
//                    )
//                )
//                initialFestivals.forEach { repository.insertFestival(it) }
//                repository.insertFestival(initialFestivals)
//            }
//            // Festivals laden
//            repository.getFestivals().collect { festivals ->
//                _festivalData.value = festivals
//            }
//        }
//    }

                // Beispiel: Ein neues Festival hinzufügen
                fun addFestival(festival: Flow<List<FestivalData>>) {
                    viewModelScope.launch {
                        festivalRepository.insertFestival(festival as List<FestivalData>)
                    }
                }
            }


