package com.example.eventum.screen_wishList.presentation.event

import com.example.eventum.screen_contacts.presentation.event.ContactsNavigationEvent
import com.example.eventum.screen_wishList.domain.model.WishList

sealed class WishListNavigationEvent {
    class NavigateToProfilePage(): WishListNavigationEvent()

    class NavigateToMainPage(): WishListNavigationEvent()
    class NavigateToContactsPage(): WishListNavigationEvent()
}