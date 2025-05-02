package com.example.eventum.screen_mainPage.data.service


import android.util.Log
import com.example.eventum.screen_mainPage.data.local.repository.EventsLocalRepository
import com.example.eventum.screen_mainPage.data.remote.repository.EventsRemoteRepository
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.model.EventRequestModel
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
        if(forceRefresh) {
            localRepository.clearEvents()
            loadRemoteToLocalData(userRemoteId)
        } else {
            val localEvents = localRepository.getEvents(userRemoteId)
            if (localEvents.isEmpty()) {
                loadRemoteToLocalData(userRemoteId)
            }
        }
        return localRepository.getEvents(userRemoteId).map {
            mapper.entityToPresentableModel(it) }
    }

    private suspend fun loadRemoteToLocalData(userId: Long) {
        val remoteCharacters = remoteRepository.getEvents(userId)
        val entities = remoteCharacters.map {
            mapper.responseToEntity(it)
        }
        localRepository.saveEvents(entities)
    }

    override suspend fun deleteEvent(event: Event): Boolean =
        try {
            localRepository.deleteEvent(event.remoteId)
            remoteRepository.delete(event.remoteId)
            true
        }
        catch (_: Exception) {
            false
        }

    override suspend fun createEvent(userRemoteId: Long, event: EventRequestModel): Event {
        val remoteEvent = remoteRepository.create(userRemoteId, mapper.requestFromModelToRemote(event))
        return mapper.remoteToPresentableModel(remoteEvent)
    }
}