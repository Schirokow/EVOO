package com.example.evoo.business.usecases

import com.example.evoo.data.EventsRepositoryImplFlow
import com.example.evoo.data.TicketmasterEvent
import kotlinx.coroutines.flow.Flow

class GetEventsUseCase() {
        private val events = EventsRepositoryImplFlow()
    fun getEventsFlow(city: String): Flow<List<TicketmasterEvent>> {
        return events.getEventsDataFlow(city)
    }
}