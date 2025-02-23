package com.example.eventum.util.mapper

import com.example.eventum.data.api.model.EventResponse
import com.example.eventum.data.roomDatabase.entity.EventEntity
import com.example.eventum.mainPage.data.remote.entity.EventRequest
import com.example.eventum.mainPage.domain.model.Event
import dagger.internal.DaggerGenerated

@DaggerGenerated
class EventMapper {
    fun responseToPresentableModel(event: EventResponse): Event {
        return Event(
            event.id,
            event.name,
            event.description,
            event.time,
            event.picture,
            event.tag
        )
    }

    fun responseToEntity(event: EventResponse): EventEntity {
        return EventEntity(
            eventId = event.id,
            name=event.name,
            description=event.description,
            time=event.time,
            picture=event.picture,
            tag=event.tag
        )
    }

    fun entityToResponse(event: EventEntity): EventResponse {
        return EventResponse(
            event.id,
            event.name,
            event.description,
            event.time,
            event.picture,
            event.tag
        )
    }

    fun modelToRequest(event: Event): EventRequest {
        return EventRequest(
            event.name,
            event.description,
            event.time,
            event.picture ?: ""
        )
    }
}