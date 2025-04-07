package com.example.eventum.util.mapper

import com.example.eventum.data.remote.model.EventRemote
import com.example.eventum.data.local.entity.EventEntity
import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest
import com.example.eventum.screen_mainPage.domain.model.Event
import dagger.internal.DaggerGenerated

@DaggerGenerated
class EventMapper {
    fun entityToPresentableModel(event: EventEntity): Event {
        return Event(
            event.id,
            event.eventId,
            event.name,
            event.description,
            event.time,
            event.picture,
            event.tag
        )
    }

    fun responseToEntity(event: EventRemote): EventEntity {
        return EventEntity(
            eventId = event.id,
            name=event.name,
            description=event.description,
            time=event.time,
            picture=event.picture,
            tag=event.tag
        )
    }

    fun entityToResponse(event: EventEntity): EventRemote {
        return EventRemote(
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

    fun updateEntity(event: Event) = EventEntity(
        event.localId,
        event.remoteId,
        event.name,
        event.description,
        event.time,
        event.tag ?: "",
        event.picture ?: ""
    )
}