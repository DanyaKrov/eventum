package com.example.eventum.screen_contacts.domain.useCase

import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.domain.repository.ContactsRepository
import javax.inject.Inject

class AddContactUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    suspend operator fun invoke(userId: Long, contact: Contact) {
        repository.createContact(userId, contact)
    }
}