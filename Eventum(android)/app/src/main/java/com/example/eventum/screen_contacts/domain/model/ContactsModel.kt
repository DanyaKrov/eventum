package com.example.eventum.screen_contacts.domain.model

data class ContactsModel (
    var isLoading: Boolean = false,
    var contacts: MutableList<Contact> = mutableListOf(),
    var errorMessage: String = ""
)