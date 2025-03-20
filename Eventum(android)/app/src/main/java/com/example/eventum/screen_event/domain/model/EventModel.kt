package com.example.eventum.screen_event.domain.model

import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_presents.domain.model.Present

data class EventModel(
    val event: Event? = null,
    val isLoading: Boolean = false,
    val notifications: MutableList<NotificationModel> = mutableListOf(),
    val errorMessage: String = ""
)