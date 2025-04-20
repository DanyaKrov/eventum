package com.example.eventum.data.remote.model.request

data class UserRemoteRequest(
    val name: String,
    val picture: String = "",
    val email: String,
    val password: String
)