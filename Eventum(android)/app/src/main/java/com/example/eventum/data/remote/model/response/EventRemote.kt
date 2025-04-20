package com.example.eventum.data.remote.model.response

import java.time.LocalDate

data class EventRemote (
    val id: Long,
    val name: String,
    val description: String,
    val time: String,
    val picture: String,
    val tag: Long,
    val userId: Long,
)