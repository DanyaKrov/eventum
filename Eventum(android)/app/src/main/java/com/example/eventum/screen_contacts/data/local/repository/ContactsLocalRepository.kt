package com.example.eventum.screen_contacts.data.local.repository

import com.example.eventum.data.local.model.entity.ContactEntity

interface ContactsLocalRepository {
    suspend fun insert(contact: ContactEntity)
    suspend fun getAll(userId: Long): List<ContactEntity>
    suspend fun updateContact(newContact: ContactEntity): String
    suspend fun deleteContact(id: Long): String
    suspend fun deleteAll(): String
}