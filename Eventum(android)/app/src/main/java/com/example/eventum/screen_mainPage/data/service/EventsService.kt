package com.example.eventum.screen_mainPage.data.service


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
    override suspend fun getEvents(userRemoteId: Long, forceRefresh: Boolean
    ): List<Event> {
        if (forceRefresh) {
            try {
                val remoteEvents = getRemoteEvents(userRemoteId)
                localRepository.clearEvents()
                localRepository.saveEvents(remoteEvents.map { mapper.responseToEntity(it) })
            } catch (e: Exception) {
                // handle it
            }
        } else {
            val localEvents = localRepository.getEvents()
            if (localEvents.isNotEmpty()) {
                localRepository.getEvents().map { mapper.entityToPresentableModel(it) }
            } else {
                val remoteEvents = getRemoteEvents(userRemoteId)
                localRepository.saveEvents(remoteEvents.map { mapper.responseToEntity(it) })
            }
        }
        return localRepository.getEvents().map { mapper.entityToPresentableModel(it) }
    }

    override suspend fun deleteEvent(event: Event): String {
        localRepository.deleteEvent(event.localId)
        return remoteRepository.delete(event.remoteId)
    }

    private suspend fun getRemoteEvents(userRemoteId: Long) =
        remoteRepository.getEvents(userRemoteId)
}