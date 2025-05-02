package com.example.eventum.screen_mainPage.data.remote.repository

import com.example.eventum.data.remote.model.response.EventRemote
import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest
import com.example.eventum.screen_mainPage.domain.model.Event

interface EventsRemoteRepository {
    suspend fun getEvents(id: Long): List<EventRemote>
    suspend fun create(userId: Long, event: EventRequest): EventRemote
    suspend fun delete(id: Long)
}