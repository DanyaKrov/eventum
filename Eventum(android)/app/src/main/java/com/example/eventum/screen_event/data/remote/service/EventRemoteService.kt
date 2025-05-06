package com.example.eventum.screen_event.data.remote.service

import android.util.Log
import com.example.eventum.data.remote.model.response.ContactRemote
import com.example.eventum.data.remote.model.response.EventRemote
import com.example.eventum.screen_event.data.remote.repository.EventRemoteRepository
import com.example.eventum.screen_mainPage.data.remote.dataSource.EventsRemoteDataSource
import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest
import javax.inject.Inject

class EventRemoteService @Inject constructor(
    private val dataSource: EventsRemoteDataSource
): EventRemoteRepository {
    override suspend fun update(id: Long, event: EventRequest): Boolean {
        return try {
            dataSource.updateById(id, event)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun getEvent(eventRemoteId: Long): EventRemote = dataSource.getEvent(eventRemoteId)
    override suspend fun createEvent(userId: Long, event: EventRequest): EventRemote =
        dataSource.create(userId, event)

    override suspend fun getEventContacts(eventId: Long): List<ContactRemote> =
        dataSource.getContacts(eventId)

    override suspend fun addContact(eventId: Long, contactId: Long): Boolean {
        return try {
            dataSource.addContact(eventId, contactId)
            true
        }
        catch (_: Exception) {
            false
        }
    }

    override suspend fun removeContact(eventId: Long, contactId: Long): Boolean {
        return try {
            dataSource.removeContact(eventId, contactId)
            true
        }
        catch (_: Exception) {
            false
        }
    }
}