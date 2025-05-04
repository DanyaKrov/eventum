package com.example.eventum.screen_event.domain.model

data class NotificationModel(
    val id: Long = 0, // id from room database
    val requestId: String = "", // id from workManager in order to cancel notification if need
    val title: String,
    val description: String = "",
    val time: String, // "yyyy-MM-dd format
    val eventOwnerId: Long,
)
