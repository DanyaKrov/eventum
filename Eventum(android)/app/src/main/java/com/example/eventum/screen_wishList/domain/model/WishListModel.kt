package com.example.eventum.screen_wishList.domain.model

import com.example.eventum.domain.model.UiState

data class WishListModel (
    val uiState: UiState = UiState(),
    var wishList: WishList? = null
)