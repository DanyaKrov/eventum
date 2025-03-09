package com.example.eventum.screen_contacts.domain.useCase

import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.domain.repository.ContactsRepository
import javax.inject.Inject

class UpdateContactUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    suspend operator fun invoke(contact: Contact): String =
        repository.editContact(contact)
}