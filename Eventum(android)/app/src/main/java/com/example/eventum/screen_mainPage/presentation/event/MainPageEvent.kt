package com.example.eventum.screen_mainPage.presentation.event

import com.example.eventum.screen_mainPage.domain.model.Event

sealed class MainPageEvent {
    class EventExpanded(val selectedEvent: Event): MainPageEvent()
    class EventDelete(val selectedEvent: Event): MainPageEvent()
    class EventEdit(val selectedEvent: Event): MainPageEvent()
}