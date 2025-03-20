package com.example.eventum.screen_event.domain.useCase

import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_event.domain.repository.EventRepository
import com.example.eventum.screen_mainPage.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetEventUseCase @Inject constructor(
    private val repository: EventRepository
) {
    operator fun invoke(remoteId: Long): Flow<Event> = flow {
        val event = repository.getEvent(remoteId)
        emit(event)
    }
}