package com.example.eventum.data.remote.model

import com.example.eventum.screen_presents.domain.model.Present

data class WishListRemote (
    val id: Long,
    val userId: Long,
    val presents: List<Present>
)