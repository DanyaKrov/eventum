package com.example.eventum.screen_mainPage.data.remote.repository

import com.example.eventum.data.remote.model.EventResponse
import com.example.eventum.screen_mainPage.domain.model.Event

interface EventsRemoteRepository {
    suspend fun get(id: Long): EventResponse
    suspend fun create(event: Event): EventResponse
    suspend fun delete(id: Long): String
    suspend fun update(id:Long, event: Event): EventResponse
}