package com.example.eventum.screen_contacts.presentation.event

import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.presentation.event.MainPageNavigationEvent

sealed class ContactsNavigationEvent {
    class NavigateToContactGiftsPage(val selectedContact: Contact): ContactsNavigationEvent()
    class NavigateToProfilePage(): ContactsNavigationEvent()

    class NavigateToMainPage(): ContactsNavigationEvent()
    class NavigateToWishListPage(): ContactsNavigationEvent()
}
