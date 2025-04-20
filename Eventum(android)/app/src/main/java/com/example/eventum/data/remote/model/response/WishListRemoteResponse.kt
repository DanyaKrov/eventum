package com.example.eventum.data.remote.model.response

import com.example.eventum.screen_presents.domain.model.Present

data class WishListRemoteResponse (
    val id: Long,
    val isAvailable: Boolean,
    val presents: List<PresentRemoteResponse>
)