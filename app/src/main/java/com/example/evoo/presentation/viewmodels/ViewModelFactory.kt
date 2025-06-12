package com.example.evoo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.evoo.business.usecases.GetFestivalUseCase
import com.example.evoo.business.usecases.GetFestivalsUseCase
import com.example.evoo.data.FestivalRepository
import com.example.evoo.data.FestivalRepositoryImpl

object AppModule {
    private val repository: FestivalRepository = FestivalRepositoryImpl()
    val getFestivalsUseCase = GetFestivalsUseCase(repository)
    val getFestivalUseCase = GetFestivalUseCase(repository)

    val homeViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(getFestivalsUseCase) as T
        }
    }

    val detailViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ContentDetailViewModel(getFestivalUseCase) as T
        }
    }
}