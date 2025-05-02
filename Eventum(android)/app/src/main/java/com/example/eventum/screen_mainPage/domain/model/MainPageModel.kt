package com.example.eventum.screen_mainPage.domain.model

import com.example.eventum.domain.model.UiState

data class MainPageModel(
    val uiState: UiState = UiState(),
    val events: MutableList<Event> = mutableListOf(),
)