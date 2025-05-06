package com.example.eventum.screen_event.data.local.repository

import com.example.eventum.data.local.model.entity.ContactEntity
import com.example.eventum.data.local.model.entity.EventEntity
import com.example.eventum.data.local.model.entity.EventWithContacts
import com.example.eventum.data.local.model.entity.EventWithNotificationsEntity
import com.example.eventum.screen_mainPage.domain.model.Event

interface EventLocalRepository {
    suspend fun getEvent(remoteId: Long): EventEntity
    suspend fun getEventWithContacts(eventId: Long): EventWithContacts
    suspend fun addContact(eventRemoteId: Long, contactRemoteId: Long): Boolean
    suspend fun deleteContact(eventRemoteId: Long, contactRemoteId: Long): Boolean
    suspend fun updateEvent(event: EventEntity): Boolean
    suspend fun createEvent(event: EventEntity)
    suspend fun getEventWithNotifications(eventId: Long): EventWithNotificationsEntity
}