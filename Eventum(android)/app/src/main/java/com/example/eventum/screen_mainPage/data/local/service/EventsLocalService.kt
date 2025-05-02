package com.example.eventum.screen_mainPage.data.local.service

import android.util.Log
import com.example.eventum.data.local.dao.EventDao
import com.example.eventum.data.local.model.entity.EventEntity
import com.example.eventum.screen_mainPage.data.local.repository.EventsLocalRepository
import javax.inject.Inject

class EventsLocalService @Inject constructor(
    private val dao: EventDao
): EventsLocalRepository {
    override suspend fun getEvents(userId: Long): List<EventEntity> =
        dao.getEvents(userId)
    override suspend fun createEvent(event: EventEntity) =
        try {
            dao.insert(event)
            true
        }
        catch (_: Exception) {
            false
        }

    override suspend fun saveEvents(events: List<EventEntity>) {
        dao.insertAll(events)
    }

    override suspend fun clearEvents() = dao.deleteAll()
    override suspend fun deleteEvent(eventId: Long) = dao.delete(eventId)
}