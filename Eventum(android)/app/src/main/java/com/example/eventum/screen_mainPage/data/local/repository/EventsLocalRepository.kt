package com.example.eventum.screen_mainPage.data.local.repository

import com.example.eventum.data.local.entity.EventEntity

interface EventsLocalRepository {
    suspend fun getEvents(): List<EventEntity>
    suspend fun saveEvents(events: List<EventEntity>)
    suspend fun clearEvents()
    suspend fun deleteEvent(eventId: Long)
}