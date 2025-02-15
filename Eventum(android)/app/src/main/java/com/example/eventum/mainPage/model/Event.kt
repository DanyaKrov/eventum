package com.example.eventum.mainPage.model

import java.time.LocalDateTime

data class Event(
    val name: String,
    val description: String,
    val time: LocalDateTime,
    val picture: String,
    val tag: String
)
