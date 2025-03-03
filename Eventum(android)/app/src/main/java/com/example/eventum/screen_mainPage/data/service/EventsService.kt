package com.example.eventum.screen_mainPage.data.service


import com.example.eventum.data.api.model.EventResponse
import com.example.eventum.screen_mainPage.data.local.repository.EventsLocalRepository
import com.example.eventum.screen_mainPage.data.remote.repository.EventsRemoteRepository
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.repository.EventsRepository
import com.example.eventum.util.mapper.EventMapper
import javax.inject.Inject

class EventsService @Inject constructor(
    private val remoteRepository: EventsRemoteRepository,
    private val localRepository: EventsLocalRepository,
    private val mapper: EventMapper
): EventsRepository {
    override suspend fun getEvents( eventsIds: List<Long>, forceRefresh: Boolean
    ): List<EventResponse> {
        return if (forceRefresh) {
            try {
                val remoteEvents = getRemoteEvents(eventsIds)
                localRepository.clearEvents()
                localRepository.saveEvents(remoteEvents.map { mapper.responseToEntity(it) })
                remoteEvents
            } catch (e: Exception) {
                localRepository.getEvents().map { mapper.entityToResponse(it) }
            }
        } else {
            val localEvents = localRepository.getEvents()
            if (localEvents.isNotEmpty()) {
                localEvents.map { mapper.entityToResponse(it) }
            } else {
                val remoteEvents = getRemoteEvents(eventsIds)
                localRepository.saveEvents(remoteEvents.map { mapper.responseToEntity(it) })
                remoteEvents
            }
        }
    }

    override suspend fun deleteEvent(event: Event): String {
        localRepository.deleteEvent(event.eventId)
        return remoteRepository.delete(event.eventId)
    }

    private suspend fun getRemoteEvents(eventsIds: List<Long>) = eventsIds.map {
            remoteRepository.get(it)
    }
}