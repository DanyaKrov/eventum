package com.example.eventum.data.remote.model.response

data class GiftRemote (
    val id: Long,
    val present: PresentRemote? = null, // on api side these parameters need to be ignored, while considering update
    val state: GiftStateRemote? = null,
    val contactId: Long,
    val title: String,
    val description: String
)