package com.example.eventum.screen_mainPage.presentation.event

import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.model.EventRequestModel

sealed class MainPageEvent {
    class EventExpanded(val selectedEvent: Event): MainPageEvent()
    class EventDelete(val selectedEvent: Event): MainPageEvent()
    class EventCreate(val createdEvent: EventRequestModel): MainPageEvent()
}