package com.example.eventum.screen_event.domain.model

import com.example.eventum.domain.model.UiState
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_presents.domain.model.Present

data class EventModel(
    val uiState: UiState = UiState(),
    val event: Event? = null
)