package com.example.eventum.model.response

data class NotificationResponse(
    val id: Long,
    var name: String,
    var description: String,
    var time: String, // input date, format: yyyy-MM-dd
    val eventId: Long
)
