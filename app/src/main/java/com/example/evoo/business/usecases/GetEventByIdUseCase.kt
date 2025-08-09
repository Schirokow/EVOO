package com.example.evoo.business.usecases

import com.example.evoo.data.EventByIdImplFlow
import com.example.evoo.data.TicketmasterEvent
import kotlinx.coroutines.flow.Flow

class GetEventByIdUseCase {
    private val event = EventByIdImplFlow()
    fun getEventByIdFlow(eventId: String): Flow<TicketmasterEvent?> {
        return event.getEventByIdFlow(eventId)
    }
}