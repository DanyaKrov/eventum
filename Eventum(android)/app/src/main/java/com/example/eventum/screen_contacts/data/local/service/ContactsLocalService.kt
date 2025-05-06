package com.example.eventum.screen_contacts.data.local.service

import android.util.Log
import com.example.eventum.data.local.dao.ContactDao
import com.example.eventum.data.local.model.entity.ContactEntity
import com.example.eventum.screen_contacts.data.local.repository.ContactsLocalRepository
import javax.inject.Inject

class ContactsLocalService @Inject constructor(
    private val dao: ContactDao
): ContactsLocalRepository {
    override suspend fun insert(contact: ContactEntity) {
        dao.insert(contact)
    }

    override suspend fun getAll(userId: Long): List<ContactEntity> = dao.getAll(userId)

    override suspend fun updateContact(newContact: ContactEntity): String {
        return try {
            dao.update(newContact)
            "Updated with success"
        }
        catch (e: Exception) {
            "Couldn't update this contact"
        }
    }

    override suspend fun deleteContact(remoteId: Long): String {
        return try {
            dao.delete(remoteId)
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