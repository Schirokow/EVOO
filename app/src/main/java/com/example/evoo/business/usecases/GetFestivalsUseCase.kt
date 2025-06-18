package com.example.evoo.business.usecases

import com.example.evoo.data.FestivalData
import com.example.evoo.data.FestivalRepository
import com.example.evoo.data.FestivalRepositoryImpl
import com.example.evoo.data.festivalDataFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/* MVVM
presentation/
├── screens/
│   ├── HomeScreen.kt
│   ├── DetailScreen.kt
│   └── ...
├── viewmodels/
│   ├── HomeViewModel.kt
│   └── DetailViewModel.kt
domain/
├── repository/
│   └── FestivalRepository.kt
├── model/
│   └── FestivalData.kt
└── usercases/
├── GetFestivalsUseCase.kt
data/
├── repository/
│   └── FestivalRepositoryImpl.kt
└── source/
└── FestivalDataSource.kt (optional)

 */

class GetFestivalsUseCase {
    private val repository = FestivalRepositoryImpl()

    fun getFestivalsFlow(): Flow<List<FestivalData>> {
        return repository.getFestivalsFlow()
    }

    fun getFestivalByIdFlow(id: Int): Flow<FestivalData?> {
        return repository.getFestivalByIdFlow(id)
    }
}



