package com.example.eventum.model.request

data class UserRequest(
    val name: String,
    val email: String,
    val picture: String = "",
)
