package com.example.eventum.screen_contacts.domain.repository

import com.example.eventum.data.remote.model.request.ContactRequest
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_presents.domain.model.Present

interface ContactsRepository {
    suspend fun getContacts(userId: Long, forceRefresh: Boolean): List<Contact>
    suspend fun deleteContact(contactId: Long): String
    suspend fun editContact(contact: Contact): String
    suspend fun createContact(userId: Long, contact: Contact)
}