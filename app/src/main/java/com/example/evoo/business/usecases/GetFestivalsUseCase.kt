package com.example.evoo.business.usecases

import com.example.evoo.data.FestivalData
import com.example.evoo.data.FestivalRepository
import kotlinx.coroutines.flow.Flow

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
└── GetFestivalUseCase.kt
data/
├── repository/
│   └── FestivalRepositoryImpl.kt
└── source/
└── FestivalDataSource.kt (optional)

 */

class GetFestivalsUseCase(private val repository: FestivalRepository) {


    // Für kontinuierlichen Flow
     fun invokeFlow(): Flow<List<FestivalData>> {
        return repository.getFestivalsFlow()
    }
}

class GetFestivalByIndexUseCase(private val repository: FestivalRepository) {
    operator fun invoke(index: Int): Flow<FestivalData?> {
        return repository.getFestivalByIndexFlow(index)
    }
}

