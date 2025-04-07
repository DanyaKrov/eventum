package com.example.eventum.screen_settings.domain.model

import com.example.eventum.data.local.preferences.model.WishListVisibility
import com.example.eventum.domain.model.UiState

data class SettingsModel (
    val uiState: UiState = UiState(),
    val isThemeDark: Boolean = false,
    val wishListVisibility: WishListVisibility? = null
)