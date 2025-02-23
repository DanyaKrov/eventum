package com.example.eventum.data.api.model

data class EventResponse (
    val id: Long,
    val name: String,
    val description: String,
    val time: String, // format of time: yyyy-MM-dd
    val picture: String,
    val tag: String, // tag of event
)