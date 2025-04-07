package com.example.eventum.screen_mainPage.domain.repository


import com.example.eventum.screen_mainPage.domain.model.Event

interface EventsRepository {
    suspend fun getEvents(eventsIds: List<Long>,
                          forceRefresh: Boolean = false): List<Event>
    suspend fun deleteEvent(event: Event): String
}