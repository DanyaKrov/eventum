package com.example.eventum.screen_event.domain.useCase

import com.example.eventum.screen_event.domain.repository.EventRepository
import com.example.eventum.screen_mainPage.domain.model.Event
import javax.inject.Inject

class UpdateEventUseCase @Inject constructor(
    private val repository: EventRepository
) {
    suspend operator fun invoke(newEvent: Event) {
        repository.updateEvent(newEvent)
    }
}