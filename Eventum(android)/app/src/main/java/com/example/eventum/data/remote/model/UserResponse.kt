package com.example.eventum.data.remote.model

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val picture: String,
    val password: String,
    val friends: List<Long>,
    val events: List<Long>,
)