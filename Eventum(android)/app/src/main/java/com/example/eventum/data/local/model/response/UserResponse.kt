package com.example.eventum.data.local.model.response

data class UserResponse (
    val remoteId: Long, // id from mysql database
    val name: String,
    val email: String,
    val picture: String,
    val friendsIds: List<Long>,
    val eventsIds: List<Long>
)