package com.example.eventum.model.response

import com.example.eventum.database.entity.User
import jakarta.persistence.Column
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

data class ContactResponse(
    var id: Long = 0,
    var name: String,
    val user: UserResponse
)