package com.example.eventum.screen_contacts.domain.model

data class ContactRequestModel (
    val name: String,
    val authorisedLogin: String? = null
)