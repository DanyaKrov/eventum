package com.example.eventum.screen_contacts.data.service

import android.util.Log
import com.example.eventum.data.remote.model.request.ContactRequest
import com.example.eventum.screen_contacts.data.local.repository.ContactsLocalRepository
import com.example.eventum.screen_contacts.data.remote.repository.ContactsRemoteRepository
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.domain.repository.ContactsRepository
import com.example.eventum.util.mapper.ContactMapper
import javax.inject.Inject

class ContactsService @Inject constructor(
    private val localRepository: ContactsLocalRepository,
    private val remoteRepository: ContactsRemoteRepository,
    private val mapper: ContactMapper
): ContactsRepository {
    override suspend fun getContacts(userId: Long, forceRefresh: Boolean): List<Contact> {
        if (forceRefresh) {
            try {
                val remoteContacts = remoteRepository.getAll(userId)
                localRepository.deleteAll()
                remoteContacts.forEach { localRepository.insert(mapper.fromRemoteToEntity(it)) }
            } catch (e: Exception) {
                localRepository.getAll(userId).map { mapper.fromEntityToModel(it) }
            }
        } else {
            val localContacts = localRepository.getAll(userId).map { mapper.fromEntityToModel(it) }
            localContacts.ifEmpty {
                val remoteContacts = remoteRepository.getAll(userId)
                remoteContacts.forEach { localRepository.insert(mapper.fromRemoteToEntity(it)) }
            }
        }
        return localRepository.getAll(userId).map { mapper.fromEntityToModel(it) }

    }

    override suspend fun deleteContact(contactId: Long): String {
        remoteRepository.delete(contactId)
        return localRepository.deleteContact(contactId)
    }

    override suspend fun editContact(contact: Contact): String {
        remoteRepository.update(contact.remoteId, mapper.fromModelToRequest(contact))
        return localRepository.updateContact(mapper.fromModelToEntity(contact, contact.remoteId))
    }

    override suspend fun createContact(userId: Long, contact: Contact) {
        val createdContact = remoteRepository.insert(userId, mapper.fromModelToRequest(contact))
        localRepository.insert(mapper.fromModelToEntity(contact, createdContact.id))
    }
}