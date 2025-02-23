package com.example.eventum.model.response

import com.example.eventum.database.entity.Tag
import java.time.LocalDateTime

data class EventResponse(
    val id: Long,
    val name: String,
    val description: String,
    val time: String, // " yyyy-MM-dd format
    val picture: String,
    val tag: String, // tag of event
    val contactsIds: List<ContactResponse> // contacts, which target in event
)