package com.example.eventum.screen_mainPage.domain.model

data class Event(
    val localId: Long = 0,
    val remoteId: Long, // id in mysql database
    val name: String,
    val description: String,
    val time: String, // yyyy-MM-dd format
    val picture: String?,
    val tag: String?,
    val userRemoteId: Long
)
