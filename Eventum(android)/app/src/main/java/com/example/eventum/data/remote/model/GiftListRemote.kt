package com.example.eventum.data.remote.model

import com.example.eventum.screen_presents.domain.model.Present

data class GiftListRemote(
    val id: Long,
    val contact: ContactRemote,
    val gifts: List<GiftRemote>
)
