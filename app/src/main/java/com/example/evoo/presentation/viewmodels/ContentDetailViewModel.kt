package com.example.evoo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evoo.business.usecases.GetFestivalUseCase
import com.example.evoo.data.FestivalData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContentDetailViewModel(private val getFestivalUseCase: GetFestivalUseCase) : ViewModel() {

    // StateFlow für FestivalData hinzufügen
//    private val _festivalData = MutableStateFlow<List<FestivalData>>(emptyList())
//    val festivalData: StateFlow<List<FestivalData>> = _festivalData.asStateFlow()


    // FestivalData laden und im StateFlow speichern
//    fun loadFestivalData() {
//        viewModelScope.launch {
//            festivalDataFlow().collect { festivalDataList ->
//                _festivalData.value = festivalDataList
//            }
//        }
//    }

    private val _festival = MutableStateFlow<FestivalData?>(null)
    val festival: StateFlow<FestivalData?> = _festival.asStateFlow()

    fun loadFestival(index: Int) {
        viewModelScope.launch {
            _festival.value = getFestivalUseCase(index)
        }
    }

}