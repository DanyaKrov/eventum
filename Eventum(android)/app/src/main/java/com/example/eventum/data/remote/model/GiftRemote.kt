package com.example.eventum.data.remote.model

import com.example.eventum.screen_presents.domain.model.Present

data class GiftRemote (
    val id: Long,
    val present: PresentRemote? = null, // on api side these parameters need to be ignored, while considering update
    val state: GiftStateRemote? = null,
    val title: String,
    val description: String
)