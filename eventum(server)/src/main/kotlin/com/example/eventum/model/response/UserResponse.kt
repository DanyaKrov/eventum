package com.example.eventum.model.response

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val picture: String,
    val password: String,
    val friends: List<Long>, // list of id of users
    val events: List<Long>, // list of id of events
)