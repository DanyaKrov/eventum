package com.example.eventum.data.remote.model.request

data class ContactRequest(
    val name: String,
    val friendLogin: String? = null
)