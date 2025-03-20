package com.example.eventum.screen_mainPage.presentation.event

sealed class MainPageNavigationEvent {
    class NavigateToSettings(): MainPageNavigationEvent()
    class ChangeToCalendarView(): MainPageNavigationEvent()
    class NavigateToPreparationsPage(): MainPageNavigationEvent()
    class NavigateToProfilePage(): MainPageNavigationEvent()
}