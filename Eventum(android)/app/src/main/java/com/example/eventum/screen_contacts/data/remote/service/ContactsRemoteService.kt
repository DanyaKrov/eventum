package com.example.eventum.screen_contacts.data.remote.service

import com.example.eventum.screen_contacts.data.remote.dataSource.ContactsRemoteDataSource
import com.example.eventum.screen_contacts.data.remote.repository.ContactsRemoteRepository
import com.example.eventum.screen_contacts.domain.model.Contact
import javax.inject.Inject

class ContactsRemoteService @Inject constructor(
    private val dataSource: ContactsRemoteDataSource
): ContactsRemoteRepository {
    override suspend fun getAll(userId: Long): List<Contact> = dataSource.getAll()

    override suspend fun delete(contactId: Long): String = dataSource.deleteById(contactId)

    override suspend fun insert(contact: Contact): Contact = dataSource.create(contact)

    override suspend fun update(id: Long, contact: Contact): String {

        return try {
            dataSource.updateById(id, contact)
            "Contact updated successfully"
        } catch (e: Exception) {
            "Error occurred. Contact wasn't updated"
        }
    }
}