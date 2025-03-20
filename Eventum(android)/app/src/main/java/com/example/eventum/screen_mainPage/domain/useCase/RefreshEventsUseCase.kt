package com.example.eventum.screen_mainPage.domain.useCase

import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.repository.EventsRepository
import com.example.eventum.util.mapper.EventMapper
import javax.inject.Inject

class RefreshEventsUseCase @Inject constructor(
    private val repository: EventsRepository,
    private val mapper: EventMapper
) {
    suspend operator fun invoke(eventsIds: List<Long>, refreshLocalDatabase: Boolean): List<Event> =
        repository.getEvents(eventsIds, refreshLocalDatabase)
}