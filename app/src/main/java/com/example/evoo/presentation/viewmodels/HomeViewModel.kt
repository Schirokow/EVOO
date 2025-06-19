package com.example.evoo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evoo.business.usecases.GetFestivalsUseCase
import com.example.evoo.data.FestivalData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeViewModel: ViewModel() {
    private val getFestivalsUseCase: GetFestivalsUseCase = GetFestivalsUseCase()
    // StateFlow für FestivalData hinzufügen
    private val _festivalData = MutableStateFlow<List<FestivalData>>(emptyList())
    val festivalData: StateFlow<List<FestivalData>> = _festivalData.asStateFlow()

    // Kontinuierlicher Flow
    init {
        viewModelScope.launch {
            getFestivalsUseCase.getFestivalsFlow().collect { festivals ->
                _festivalData.value = festivals
            }
        }
    }

}