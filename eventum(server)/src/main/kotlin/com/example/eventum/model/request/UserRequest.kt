package com.example.eventum.model.request

data class UserRequest(
    val name: String,
    val email: String,
    val picture: String = "",
    val password: String,
    val friends: List<Long> = listOf(),
    val events: List<Long> = emptyList(),
)
