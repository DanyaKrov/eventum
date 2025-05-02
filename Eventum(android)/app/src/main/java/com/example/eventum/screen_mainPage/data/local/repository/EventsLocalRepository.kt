package com.example.eventum.screen_mainPage.data.local.repository

import com.example.eventum.data.local.model.entity.EventEntity

interface EventsLocalRepository {
    suspend fun getEvents(userId: Long): List<EventEntity>
    suspend fun createEvent(event: EventEntity): Boolean
    suspend fun saveEvents(events: List<EventEntity>)
    suspend fun clearEvents()
    suspend fun deleteEvent(eventId: Long)
}