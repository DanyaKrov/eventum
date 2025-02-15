package com.example.eventum.model.request

import com.example.eventum.database.entity.Contact
import com.example.eventum.database.entity.Tag
import com.example.eventum.database.entity.User
import java.time.LocalDateTime

data class EventRequest(
    var name: String,
    var description: String,
    var time: LocalDateTime,
    var picture: String = "",
    var tag: Tag? = null, // tags of event
    var usersIds: MutableSet<User> = mutableSetOf(), // users, which target in event
    var contactsIds: MutableSet<Contact> = mutableSetOf(), // contacts, which target in event
)
