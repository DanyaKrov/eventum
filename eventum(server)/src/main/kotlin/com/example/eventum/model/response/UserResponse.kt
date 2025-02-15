package com.example.eventum.model.response

import com.example.eventum.database.entity.User

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val picture: String,
    val users : MutableSet<User>,
    val password: String
)