package com.example.eventum.screen_event.domain.model

import com.example.eventum.domain.model.UiState

data class NotificationsModel (
    val uiState: UiState = UiState(),
    val notifications: MutableList<NotificationModel> = mutableListOf()
)