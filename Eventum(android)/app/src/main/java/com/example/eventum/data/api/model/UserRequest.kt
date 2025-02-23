package com.example.eventum.data.api.model

data class UserRequest(
    val name: String,
    val email: String,
    val picture: String = "",
)