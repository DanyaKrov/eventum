package com.example.eventum.screen_contacts.presentation.event

import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.presentation.sort.SortOrder

sealed class ContactsEvent {
    class SortTagContactsEvent(val tag: String): ContactsEvent()
    class SortContactsEvent(val order: SortOrder): ContactsEvent()
    class EditContactEvent(val contact: Contact): ContactsEvent()
}