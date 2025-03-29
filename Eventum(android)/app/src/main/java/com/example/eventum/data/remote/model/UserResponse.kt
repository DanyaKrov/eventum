package com.example.eventum.data.remote.model

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val picture: String,
    val password: String = "", // for now, password parameter will be ignored for update request
    val friends: List<Long> = listOf(),
    val events: List<Long> = listOf(),
)