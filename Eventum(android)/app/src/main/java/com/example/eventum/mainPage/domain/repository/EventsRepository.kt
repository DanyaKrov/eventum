package com.example.eventum.mainPage.domain.repository


import com.example.eventum.data.api.model.EventResponse
import com.example.eventum.mainPage.domain.model.Event

interface EventsRepository {
    suspend fun getEvents(eventsIds: List<Long>,
                          forceRefresh: Boolean = false): List<EventResponse>
    suspend fun deleteEvent(event: Event): String
}