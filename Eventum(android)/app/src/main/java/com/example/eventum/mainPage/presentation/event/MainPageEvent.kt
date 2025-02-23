package com.example.eventum.mainPage.presentation.event

import com.example.eventum.mainPage.domain.model.Event

sealed class MainPageEvent {
    class EventExpanded(val eventNumber: Long): MainPageEvent()
    class EventDelete(val event: Event): MainPageEvent()
    class EventEdit(val eventNumber: Long): MainPageEvent()
}