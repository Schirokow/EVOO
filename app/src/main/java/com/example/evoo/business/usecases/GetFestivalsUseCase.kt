package com.example.evoo.business.usecases

import com.example.evoo.data.FestivalData
import com.example.evoo.data.FestivalRepository

class GetFestivalsUseCase(private val repository: FestivalRepository) {
    suspend operator fun invoke(): List<FestivalData> {
        return repository.getFestivals()
    }
}

// Optional: Use Case für einzelnes Festival
class GetFestivalUseCase(private val repository: FestivalRepository) {
    suspend operator fun invoke(index: Int): FestivalData? {
        val festivals = repository.getFestivals()
        return if (index in festivals.indices) festivals[index] else null
    }
}