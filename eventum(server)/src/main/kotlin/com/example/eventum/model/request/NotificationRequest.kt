package com.example.eventum.model.request

data class NotificationRequest(
    var name: String = "",
    var description: String = "",
    var time: String, // input date, format: yyyy-MM-dd
    val eventId: Long
)
