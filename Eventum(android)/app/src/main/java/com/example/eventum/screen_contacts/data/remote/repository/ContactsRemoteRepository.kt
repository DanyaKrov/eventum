package com.example.eventum.screen_contacts.data.remote.repository

import com.example.eventum.data.remote.model.request.ContactRequest
import com.example.eventum.data.remote.model.response.ContactRemote
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_presents.domain.model.Present

interface ContactsRemoteRepository {
    suspend fun getAll(userId: Long): List<ContactRemote>
    suspend fun delete(contactId: Long)
    suspend fun insert(userId: Long, contact: ContactRequest): ContactRemote
    suspend fun update(id: Long, contact: ContactRequest): String
}