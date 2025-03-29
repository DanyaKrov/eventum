package com.example.eventum.screen_mainPage.presentation.event

sealed class MainPageNavigationEvent {
    class ChangeToCalendarView(): MainPageNavigationEvent()
    class NavigateToEventPage(): MainPageNavigationEvent()
    class NavigateToProfilePage(): MainPageNavigationEvent()
}