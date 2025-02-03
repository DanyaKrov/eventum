package com.example.eventum.api.model

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val picture: String,
    val users : MutableSet<UserResponse>
)