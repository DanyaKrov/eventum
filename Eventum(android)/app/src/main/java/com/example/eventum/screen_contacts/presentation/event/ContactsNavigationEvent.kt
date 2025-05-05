package com.example.eventum.screen_contacts.presentation.event

import com.example.eventum.screen_mainPage.presentation.event.MainPageNavigationEvent

sealed class ContactsNavigationEvent {
    class NavigateToProfilePage(): ContactsNavigationEvent()

    class NavigateToMainPage(): ContactsNavigationEvent()
}
