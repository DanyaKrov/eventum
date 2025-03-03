package com.example.eventum.screen_presents.presentation.event

sealed class PresentsNavigationEvent {
    class NavigateBack(): PresentsNavigationEvent()
}