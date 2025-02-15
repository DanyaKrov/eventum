package com.example.eventum.mainPage.event

sealed class NavigationEvent {
    class NavigateToSettings(): NavigationEvent()
    class ChangeToCalendarView(): NavigationEvent()
    class NavigateToPreparationsPage(): NavigationEvent()
    class NavigateToProfilePage(): NavigationEvent()
}