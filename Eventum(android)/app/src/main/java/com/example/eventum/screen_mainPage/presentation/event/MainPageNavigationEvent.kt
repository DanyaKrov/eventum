package com.example.eventum.screen_mainPage.presentation.event

sealed class MainPageNavigationEvent {
    class ChangeToCalendarView(): MainPageNavigationEvent()
    class NavigateToProfilePage(): MainPageNavigationEvent()

    class NavigateToContactsPage(): MainPageNavigationEvent()

    class NavigateToWishListPage(): MainPageNavigationEvent()
}