package com.example.eventum.screen_event.data.remote.service

import com.example.eventum.data.remote.model.response.EventRemote
import com.example.eventum.screen_event.data.remote.repository.EventRemoteRepository
import com.example.eventum.screen_mainPage.data.remote.dataSource.EventsRemoteDataSource
import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest
import javax.inject.Inject

class EventRemoteService @Inject constructor(
    private val dataSource: EventsRemoteDataSource
): EventRemoteRepository {
    override suspend fun update(id: Long, event: EventRequest): Boolean {
        return try {
            dataSource.updateById(id, event)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun getEvent(eventRemoteId: Long): EventRemote = dataSource.getEvent(eventRemoteId)
}