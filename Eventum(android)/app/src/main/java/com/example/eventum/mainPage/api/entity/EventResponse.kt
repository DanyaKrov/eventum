package com.example.eventum.mainPage.api.entity

import com.example.eventum.api.model.UserResponse
import java.time.LocalDateTime

data class EventResponse(
    val id: Long,
    val name: String,
    val description: String,
    val time: LocalDateTime,
    val picture: String,
    val tag: String, // tag of event
    val usersIds: List<UserResponse>, // users, which target in event
    val contactsIds: List<ContactResponse>, // contacts, which target in event
)