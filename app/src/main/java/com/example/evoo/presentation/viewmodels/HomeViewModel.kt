package com.example.evoo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evoo.R
//import com.example.evoo.business.usecases.GetFestivalsUseCase
import com.example.evoo.data.FestivalData
import com.example.evoo.data.FestivalRepository
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

class HomeViewModel(private val repository: FestivalRepository) : ViewModel() {
    private val _festivalData = MutableStateFlow<List<FestivalData>>(emptyList())
    val festivalData: StateFlow<List<FestivalData>> = _festivalData.asStateFlow()

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

    init {
        viewModelScope.launch {
            // Prüfen, ob die Datenbank leer ist
            if (repository.getFestivals().first().isEmpty()) {
                // Beispieldaten einfügen
                val initialFestivals = listOf(
                    FestivalData(
                        imageId = R.drawable.festival1,
                        title = "Summer Festival",
                        description = "Techno Festival",
                        datum = "20 & 21 Juni 2025",
                        location = "Am Strand"
                    ),
                    FestivalData(
                        imageId = R.drawable.festival2,
                        title = "Heaven & Hill Neukirchen",
                        description = "Disco-Fest",
                        datum = "20 & 21 Juni 2025",
                        location = "Neukirchen-Vluyn"
                    ),
                    // Weitere Festivals hier hinzufügen...
                    FestivalData(
                        imageId = R.drawable.festival12,
                        title = "Latin Airport Festival",
                        description = "Latino Festival",
                        datum = "05.Juli 2025",
                        location = "Airport Nürnberg"
                    )
                )
                initialFestivals.forEach { repository.insertFestival(it) }
            }
            // Festivals laden
            repository.getFestivals().collect { festivals ->
                _festivalData.value = festivals
            }
        }
    }

    // Beispiel: Ein neues Festival hinzufügen
    fun addFestival(festival: FestivalData) {
        viewModelScope.launch {
            repository.insertFestival(festival)
        }
    }
}