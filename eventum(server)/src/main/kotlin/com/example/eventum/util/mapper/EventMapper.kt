package com.example.eventum.util.mapper

import com.example.eventum.database.entity.Event
import com.example.eventum.model.request.EventRequest
import com.example.eventum.model.response.EventResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class EventMapper(
    private val contactMapper: ContactMapper
){
    val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE // formatter for input date, format: yyyy-MM-dd


    fun entityToResponse(event: Event): EventResponse {
        return EventResponse(event.id,
            event.name,
            event.description,
            event.time.toString(),
            event.picture,
            event.tag?.name ?: "",
            event.contactsIds.map { contactMapper.entityToResponse(it) }
            )
    }

    fun updateEvent(event: Event, newEvent: EventRequest): Event = event.apply {
        event.name = newEvent.name
        event.description = newEvent.description
        event.time = LocalDate.parse(newEvent.time, dateFormatter)
        event.picture = newEvent.picture
        event.tag = newEvent.tag
        newEvent.contactsIds
        newEvent.usersIds
    }

    fun createEvent(event: EventRequest): Event = Event(
        name =event.name,
        description =event.description,
        time = LocalDate.parse(event.time, dateFormatter),
        picture =event.picture,
        tag =event.tag
    )
}