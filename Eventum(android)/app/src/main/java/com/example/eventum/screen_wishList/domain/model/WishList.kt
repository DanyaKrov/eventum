package com.example.eventum.screen_wishList.domain.model

import com.example.eventum.screen_presents.domain.model.Present

data class WishList(
    var remoteId: Long = 0,
    val presents: List<Present>,
    val userId: Long
)
