package com.example.eventum.screen_event.data.remote.repository

import com.example.eventum.data.remote.model.response.EventRemote
import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest

interface EventRemoteRepository {
    suspend fun update(id: Long, event: EventRequest): Boolean
    suspend fun get(remoteId: Long): EventRemote
}