package com.example.eventum.screen_event.data.local.repository

import com.example.eventum.data.local.entity.EventEntity
import com.example.eventum.data.local.entity.EventWithNotificationsEntity
import com.example.eventum.screen_mainPage.domain.model.Event

interface EventLocalRepository {
    suspend fun getEvent(remoteId: Long): EventEntity
    suspend fun updateEvent(event: EventEntity): Boolean
    suspend fun createEvent(event: EventEntity)
    suspend fun getEventWithNotifications(eventId: Long): EventWithNotificationsEntity
}