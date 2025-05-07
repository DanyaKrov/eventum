package com.example.eventum.screen_contacts.data.service

import android.util.Log
import com.example.eventum.data.remote.model.request.ContactRequest
import com.example.eventum.screen_contacts.data.local.repository.ContactsLocalRepository
import com.example.eventum.screen_contacts.data.remote.repository.ContactsRemoteRepository
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.domain.model.ContactRequestModel
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
                remoteContacts.forEach {
                    val entity = mapper.fromRemoteToEntity(it)
                    localRepository.insert(entity)
                }
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

    override suspend fun deleteContact(contactId: Long) {
        remoteRepository.delete(contactId)
        localRepository.deleteContact(contactId)
    }

    override suspend fun editContact(contact: Contact): String {
        remoteRepository.update(contact.remoteId, mapper.fromModelToUpdateRequest(contact))
        return localRepository.updateContact(mapper.fromModelToEntity(contact, contact.remoteId))
    }

    override suspend fun createContact(userId: Long, contact: ContactRequestModel) {
        val createdContact = remoteRepository.insert(userId, mapper.fromModelToRequest(contact))
        val entity = mapper.fromRemoteToEntity(createdContact)
        localRepository.insert(entity)
    }
}