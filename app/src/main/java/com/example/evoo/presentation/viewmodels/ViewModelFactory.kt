package com.example.evoo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.evoo.business.usecases.GetFestivalByIdUseCase
import com.example.evoo.business.usecases.GetFestivalsUseCase
import com.example.evoo.data.FestivalRepository
import com.example.evoo.data.FestivalRepositoryImpl

object AppModule {
    private val repository: FestivalRepository = FestivalRepositoryImpl()
    val getFestivalsUseCase = GetFestivalsUseCase(repository)
    val getFestivalByIdUseCase = GetFestivalByIdUseCase(repository)

    val detailViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ContentDetailViewModel(getFestivalByIdUseCase) as T
        }
    }

    val homeViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(getFestivalsUseCase) as T
        }
    }
}