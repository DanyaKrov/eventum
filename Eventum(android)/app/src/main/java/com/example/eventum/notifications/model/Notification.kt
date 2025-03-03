package com.example.eventum.notifications.model

import java.time.LocalDate

data class Notification (
    val id: Int, // id as presented in mysql database
    val title: String,
    val description: String,
    val time: LocalDate,
    val eventId: Long // id of event
)