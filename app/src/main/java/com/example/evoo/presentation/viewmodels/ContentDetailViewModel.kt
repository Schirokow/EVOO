package com.example.evoo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evoo.business.usecases.GetFestivalByIdUseCase
import com.example.evoo.data.FestivalData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContentDetailViewModel(private val getFestivalByIdUseCase: GetFestivalByIdUseCase) : ViewModel() {

    private val _festival = MutableStateFlow<FestivalData?>(null)
    val festival: StateFlow<FestivalData?> = _festival.asStateFlow()

    fun loadFestival(id: Int) {
        viewModelScope.launch {
            getFestivalByIdUseCase(id).collect { festivalData ->
                _festival.value = festivalData
            }
        }
    }
}