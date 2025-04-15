package com.example.eventum.data.remote.model.response

data class EventRemote (
    val id: Long,
    val name: String,
    val description: String,
    val time: String, // format of time: yyyy-MM-dd
    val picture: String,
    val tag: String, // tag of event
    val userRemoteId: Long

)