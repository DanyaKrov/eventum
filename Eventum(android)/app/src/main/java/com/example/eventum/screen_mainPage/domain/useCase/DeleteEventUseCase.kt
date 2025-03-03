package com.example.eventum.screen_mainPage.domain.useCase

import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.repository.EventsRepository
import javax.inject.Inject

class DeleteEventUseCase @Inject constructor(
    private val repository: EventsRepository
) {
    suspend operator fun invoke(event: Event) {
        // handle how it went
        repository.deleteEvent(event)
    }
}