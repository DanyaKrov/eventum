package com.example.eventum.screen_mainPage.domain.repository


import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.model.EventRequestModel

interface EventsRepository {
    suspend fun getEvents(userRemoteId: Long,
                          forceRefresh: Boolean = false): List<Event>
    suspend fun deleteEvent(event: Event): Boolean

    suspend fun createEvent(userRemoteId: Long, event: EventRequestModel): Event
}