package com.example.evoo.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.evoo.business.usecases.GetEventByIdUseCase
import com.example.evoo.business.usecases.GetEventsUseCase
import com.example.evoo.presentation.viewmodels.ContentDetailViewModel
import com.example.evoo.presentation.viewmodels.FavoriteViewModel
import com.example.evoo.presentation.viewmodels.HomeViewModel

object AppModule {

    private fun provideFavoriteRepository(context: Context): FavoriteRepository {
        return FavoriteRepositoryImpl(DatabaseProvider.provideFavoriteDao(context))

   }

    // Füge eine Funktion hinzu, um das EventsRepository bereitzustellen
    private fun provideEventsRepository(): EventsRepository {
        return EventsRepositoryImpl()
    }

    // Füge eine Funktion hinzu, um den GetEventsUseCase bereitzustellen
    private fun provideEventsUseCase(): GetEventsUseCase {
        return GetEventsUseCase()
    }

    private fun provideGetEventByIdUseCase(eventsRepository: EventsRepository): GetEventByIdUseCase {
        return GetEventByIdUseCase()
    }

    fun provideHomeViewModelFactory(context: Context): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return HomeViewModel(provideFavoriteRepository(context)) as T
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
                    // Erstelle ContentDetailViewModel mit allen drei richtigen Abhängigkeiten
                    return ContentDetailViewModel(
                        provideFavoriteRepository(context),
                        provideEventsUseCase(),
                    ) as T
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