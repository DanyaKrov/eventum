package com.example.eventum.feature_notifications.model

import java.time.LocalDate

data class Notification (
    val id: Long, // id as presented in room database
    val title: String,
    val description: String,
    val time: LocalDate,
    val eventId: Long // id of event
)