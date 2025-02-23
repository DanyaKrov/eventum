package com.example.eventum.model.request

import com.example.eventum.database.entity.Contact
import com.example.eventum.database.entity.Tag
import com.example.eventum.database.entity.User
import java.time.LocalDateTime

data class EventRequest(
    var name: String,
    var description: String = "",
    var time: String,
    var picture: String = "",
    var tag: Tag? = null, // tags of event
    var usersIds: List<Long> = listOf(), // users' ids, which target in event
    var contactsIds: List<Long> = listOf(), // contacts' ids, which target in event
)
