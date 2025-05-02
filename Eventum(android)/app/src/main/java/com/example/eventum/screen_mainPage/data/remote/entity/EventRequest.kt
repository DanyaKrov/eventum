package com.example.eventum.screen_mainPage.data.remote.entity

data class EventRequest(
    var name: String,
    var description: String = "",
    var time: String, // format "dd-MM-yyyy"
    var picture: String = "",
    var tagId: Long = 0,
    var contactsIds: List<Long> = listOf(), // contacts' ids, which target in event
    )
