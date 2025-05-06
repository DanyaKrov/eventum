package com.example.eventum.screen_contacts.data.remote.service

import com.example.eventum.data.remote.model.request.ContactRequest
import com.example.eventum.data.remote.model.response.ContactRemote
import com.example.eventum.screen_contacts.data.remote.dataSource.ContactsRemoteDataSource
import com.example.eventum.screen_contacts.data.remote.repository.ContactsRemoteRepository
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_mainPage.data.remote.entity.ContactResponse
import com.example.eventum.util.mapper.ContactMapper
import javax.inject.Inject

class ContactsRemoteService @Inject constructor(
    private val dataSource: ContactsRemoteDataSource
): ContactsRemoteRepository {
    override suspend fun getAll(userId: Long): List<ContactRemote> =
        dataSource.getUserContacts(userId)

    override suspend fun delete(contactId: Long) = dataSource.deleteById(contactId)

    override suspend fun insert(userId: Long, contact: ContactRequest): ContactRemote =
        dataSource.create(userId, contact)

    override suspend fun update(id: Long, contact: ContactRequest): String {

        return try {
            dataSource.updateById(id, contact)
            "Contact updated successfully"
        } catch (e: Exception) {
            "Error occurred. Contact wasn't updated"
        }
    }
}