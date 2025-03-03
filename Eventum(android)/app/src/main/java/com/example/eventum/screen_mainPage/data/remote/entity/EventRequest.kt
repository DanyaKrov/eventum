package com.example.eventum.screen_mainPage.data.remote.entity

data class EventRequest(
    var name: String,
    var description: String = "",
    var time: String,
    var picture: String = "",
    var usersIds: List<Long> = listOf(), // users' ids, which target in event
    var contactsIds: List<Long> = listOf(), // contacts' ids, which target in event
)
