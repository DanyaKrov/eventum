package com.example.eventum.screen_profile.presentation.event

sealed class ProfileNavigationEvent {
    class NavigateToMainPage(): ProfileNavigationEvent()
    class NavigateToSettingsPage(): ProfileNavigationEvent()
    class NavigateToContactsPage(): ProfileNavigationEvent()
    class ExitFromAccount(): ProfileNavigationEvent() // log out button
    class NavigateToWishListPage():ProfileNavigationEvent()
}