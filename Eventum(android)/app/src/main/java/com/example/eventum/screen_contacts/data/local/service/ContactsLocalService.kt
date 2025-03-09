package com.example.eventum.screen_contacts.data.local.service

import com.example.eventum.data.roomDatabase.dao.ContactDao
import com.example.eventum.data.roomDatabase.entity.ContactEntity
import com.example.eventum.screen_contacts.data.local.repository.ContactsLocalRepository
import com.example.eventum.screen_contacts.domain.model.Contact
import javax.inject.Inject

class ContactsLocalService @Inject constructor(
    private val dao: ContactDao
): ContactsLocalRepository {
    override suspend fun insert(contact: ContactEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<ContactEntity> = dao.getAll()

    override suspend fun updateContact(newContact: ContactEntity): String {
        return try {
            dao.update(newContact)
            "Updated with success"
        }
        catch (e: Exception) {
            "Couldn't update this contact"
        }
    }

    override suspend fun deleteContact(id: Long): String {
        return try {
            dao.delete(id)
            "Deleted with success"
        }
        catch (e: Exception) {
            "Couldn't delete this present"
        }
    }

    override suspend fun deleteAll(): String {
        return try {
            dao.deleteAll()
            "Deleted with success"
        }
        catch (e: Exception) {
            "Couldn't delete this present"
        }
    }
}