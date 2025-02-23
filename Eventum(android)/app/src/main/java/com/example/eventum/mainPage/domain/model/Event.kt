package com.example.eventum.mainPage.domain.model

import java.time.LocalDateTime

data class Event(
    val eventId: Long, // id in mysql database
    val name: String,
    val description: String,
    val time: String,
    val picture: String?,
    val tag: String?
)
