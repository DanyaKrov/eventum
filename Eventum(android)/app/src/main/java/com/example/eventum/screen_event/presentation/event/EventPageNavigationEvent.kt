package com.example.eventum.screen_event.presentation.event

sealed class EventPageNavigationEvent {
    class MoveBack(): EventPageNavigationEvent()
}