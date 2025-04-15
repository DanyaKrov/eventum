package com.example.eventum.screen_mainPage.data.remote.entity

import com.example.eventum.data.remote.model.response.UserRemote

data class ContactResponse(
    var id: Long = 0,
    var name: String,
    val user: UserRemote // user with whom associated this contact
)