package com.example.eventum.screen_profile.domain.model

import com.example.eventum.domain.model.UiState
import com.example.eventum.domain.model.User

data class ProfileModel(
    val uiState: UiState = UiState(),
    val user: User? = null,
)