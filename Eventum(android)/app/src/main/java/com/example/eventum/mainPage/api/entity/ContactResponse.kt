package com.example.eventum.mainPage.api.entity

import com.example.eventum.api.model.UserResponse

data class ContactResponse(
    var id: Long = 0,
    var name: String,
    val user: UserResponse // user with whom associated this contact
)