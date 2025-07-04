package com.example.evoo.data
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.evoo.data.DatabaseProvider
import com.example.evoo.data.FestivalRepository
import com.example.evoo.data.FestivalRepositoryImpl
import com.example.evoo.data.FavoriteRepository
import com.example.evoo.data.FavoriteRepositoryImpl
import com.example.evoo.presentation.viewmodels.ContentDetailViewModel
import com.example.evoo.presentation.viewmodels.FavoriteViewModel

import com.example.evoo.presentation.viewmodels.HomeViewModel

object AppModule {
    private fun provideFestivalRepository(context: Context): FestivalRepository {
        return FestivalRepositoryImpl(DatabaseProvider.provideFestivalDao(context))
    }

    private fun provideFavoriteRepository(context: Context): FavoriteRepository {
        return FavoriteRepositoryImpl(DatabaseProvider.provideFavoriteDao(context))

   }

    fun provideHomeViewModelFactory(context: Context): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return HomeViewModel(provideFestivalRepository(context), provideFavoriteRepository(context)) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    fun provideDetailViewModelFactory(context: Context): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ContentDetailViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return ContentDetailViewModel(provideFestivalRepository(context), provideFavoriteRepository(context)) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    fun provideFavoriteViewModelFactory(context: Context): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return FavoriteViewModel(provideFavoriteRepository(context)) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}

//Erklärung:
//provideFestivalRepository erstellt das FestivalRepository mit einem FestivalDao aus der FestivalDatabase.
//Die Factories (provideHomeViewModelFactory und provideDetailViewModelFactory) benötigen den Context, um die Datenbank zu initialisieren.
//Die Factories werden verwendet, um die ViewModels mit dem korrekten FestivalRepository zu instanziieren.