package com.example.eventum.screen_event.data.local.service

import android.util.Log
import com.example.eventum.data.local.dao.EventDao
import com.example.eventum.data.local.model.entity.ContactEntity
import com.example.eventum.data.local.model.entity.EventContactsCrossRef
import com.example.eventum.data.local.model.entity.EventEntity
import com.example.eventum.data.local.model.entity.EventWithContacts
import com.example.eventum.data.local.model.entity.EventWithNotificationsEntity
import com.example.eventum.screen_event.data.local.repository.EventLocalRepository
import javax.inject.Inject

class EventLocalService @Inject constructor(
    private val dao: EventDao
): EventLocalRepository {
    override suspend fun getEvent(remoteId: Long): EventEntity = dao.getByRemoteId(remoteId)
    override suspend fun getEventWithContacts(eventId: Long): EventWithContacts =
        dao.getEventWithContactsEntity(eventId)

    override suspend fun addContact(eventRemoteId: Long, contactRemoteId: Long): Boolean {
        return try {
            val crossRef = EventContactsCrossRef(eventId = eventRemoteId,
                contactId = contactRemoteId)
            dao.addContactRef(crossRef)
            true
        }
        catch (_: Exception) {
            false
        }
    }

    override suspend fun deleteContact(eventRemoteId: Long, contactRemoteId: Long): Boolean {
        return try {
            val crossRef = EventContactsCrossRef(eventId = eventRemoteId,
                contactId = contactRemoteId)
            dao.deleteContactRef(crossRef)
            true
        }
        catch (_: Exception) {
            false
        }
    }

    override suspend fun updateEvent(event: EventEntity): Boolean {
        return try {
            dao.update(event)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun createEvent(event: EventEntity) {
        dao.insert(event)
    }

    override suspend fun getEventWithNotifications(eventId: Long): EventWithNotificationsEntity =
        dao.getEventWithNotificationsEntity(eventId)
}