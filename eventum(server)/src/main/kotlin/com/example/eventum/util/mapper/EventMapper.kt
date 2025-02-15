package com.example.eventum.util.mapper

import com.example.eventum.database.entity.Event
import com.example.eventum.model.request.EventRequest
import com.example.eventum.model.response.EventResponse
import org.springframework.stereotype.Component

@Component
class EventMapper(
    private val contactMapper: ContactMapper,
    private val userMapper: UserMapper
){
    fun entityToResponse(event: Event): EventResponse {
        return EventResponse(event.id,
            event.name,
            event.description,
            event.time,
            event.picture,
            event.tag?.name ?: "",
            event.usersIds.map { userMapper.entityToResponse(it) },
            event.contactsIds.map { contactMapper.entityToResponse(it) }
            )
    }

    fun updateEvent(event: Event, newEvent: EventRequest): Event = event.apply {
        event.name = newEvent.name
        event.description = newEvent.description
        event.time = newEvent.time
        event.picture = newEvent.picture
        event.tag = newEvent.tag
        newEvent.contactsIds
        newEvent.usersIds
    }

    fun createEvent(event: EventRequest): Event = Event(
        name =event.name,
        description =event.description,
        time =event.time,
        picture =event.picture,
        tag =event.tag,
        contactsIds =event.contactsIds,
        usersIds =event.usersIds
    )
}