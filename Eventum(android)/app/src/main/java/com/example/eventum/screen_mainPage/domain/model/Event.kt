package com.example.eventum.screen_mainPage.domain.model

data class Event(
    val eventId: Long, // id in mysql database
    val name: String,
    val description: String,
    val time: String,
    val picture: String?,
    val tag: String?
)
