package com.example.eventum.screen_contacts.data.service

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
        return if (forceRefresh) {
            try {
                val remoteContacts = remoteRepository.getAll(userId).map { mapper.fromRemoteToModel(it) }
                localRepository.deleteAll()
                remoteContacts.forEach {localRepository.insert(mapper.fromModelToEntity(it))}
                remoteContacts
            } catch (e: Exception) {
                localRepository.getAll(userId).map { mapper.fromEntityToModel(it) }
            }
        } else {
            val localEvents = localRepository.getAll(userId).map { mapper.fromEntityToModel(it) }
            localEvents.ifEmpty {
                val remoteEvents =
                    remoteRepository.getAll(userId).map { mapper.fromRemoteToModel(it) }
                remoteEvents.forEach { localRepository.insert(mapper.fromModelToEntity(it)) }
                remoteEvents
            }
        }
    }

    override suspend fun deleteContact(contactId: Long): String {
        remoteRepository.delete(contactId)
        return localRepository.deleteContact(contactId)
    }

    override suspend fun editContact(contact: Contact): String {
        remoteRepository.update(contact.id, mapper.fromModelToRequest(contact))
        return localRepository.updateContact(mapper.fromModelToEntity(contact))
    }

    override suspend fun createContact(userId: Long, contact: Contact) {
        remoteRepository.insert(userId, mapper.fromModelToRequest(contact))
        localRepository.insert(mapper.fromModelToEntity(contact))
    }
}