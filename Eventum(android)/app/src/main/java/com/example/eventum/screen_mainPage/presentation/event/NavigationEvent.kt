package com.example.eventum.screen_mainPage.presentation.event

sealed class NavigationEvent {
    class NavigateToSettings(): NavigationEvent()
    class ChangeToCalendarView(): NavigationEvent()
    class NavigateToPreparationsPage(): NavigationEvent()
    class NavigateToProfilePage(): NavigationEvent()
}