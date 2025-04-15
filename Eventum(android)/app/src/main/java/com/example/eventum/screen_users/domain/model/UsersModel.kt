package com.example.eventum.screen_users.domain.model

import com.example.eventum.domain.model.UiState

data class UsersModel(
    val uiState: UiState = UiState(),
    val users: MutableList<UserModel>
)
