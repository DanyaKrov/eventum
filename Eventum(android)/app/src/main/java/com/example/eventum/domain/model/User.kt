package com.example.eventum.domain.model

data class User(
    val localId: Long = 0,
    val remoteId: Long, // id from mysql database
    var name: String,
    var email: String,
    var picture: String = "",
    var friends: List<Long> = listOf(),
    var events: List<Long> = listOf()
)
