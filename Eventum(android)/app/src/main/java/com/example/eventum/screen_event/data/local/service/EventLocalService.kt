package com.example.eventum.screen_event.data.local.service

import com.example.eventum.data.local.dao.EventDao
import com.example.eventum.data.local.entity.EventEntity
import com.example.eventum.data.local.entity.EventWithNotificationsEntity
import com.example.eventum.screen_event.data.local.repository.EventLocalRepository
import javax.inject.Inject

class EventLocalService @Inject constructor(
    private val dao: EventDao
): EventLocalRepository {
    override suspend fun getEvent(remoteId: Long): EventEntity = dao.getByRemoteId(remoteId)

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