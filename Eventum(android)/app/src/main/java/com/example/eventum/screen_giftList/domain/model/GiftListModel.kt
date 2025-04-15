package com.example.eventum.screen_giftList.domain.model

import com.example.eventum.domain.model.UiState

data class GiftListModel (
    val uiState: UiState = UiState(),
    val gifts: MutableList<Gift>? = null
)