package com.example.eventum.screen_event.data.remote.repository

import com.example.eventum.data.remote.model.EventResponse
import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest
import com.example.eventum.screen_mainPage.domain.model.Event

interface EventRemoteRepository {
    suspend fun update(id: Long, event: EventRequest): Boolean
    suspend fun get(remoteId: Long): EventResponse
}