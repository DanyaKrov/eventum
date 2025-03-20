package com.example.eventum.screen_mainPage.data.local.service

import com.example.eventum.data.local.dao.EventDao
import com.example.eventum.data.local.entity.EventEntity
import com.example.eventum.screen_mainPage.data.local.repository.EventsLocalRepository
import javax.inject.Inject

class EventsLocalService @Inject constructor(
    private val dao: EventDao
): EventsLocalRepository {
    override suspend fun getEvents(): List<EventEntity> = dao.getAll()

    override suspend fun saveEvents(events: List<EventEntity>) {
        events.forEach {
            dao.insert(it)
        }
    }

    override suspend fun clearEvents() = dao.deleteAll()
    override suspend fun deleteEvent(eventId: Long) = dao.delete(eventId)
}