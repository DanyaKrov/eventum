package com.example.eventum.screen_contacts.presentation.event

sealed class ContactsNavigationEvent {
    class MoveBackEvent(): ContactsNavigationEvent()
}
