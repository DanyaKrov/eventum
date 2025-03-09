package com.example.eventum.screen_contacts.domain.useCase

import com.example.eventum.screen_contacts.domain.repository.ContactsRepository
import javax.inject.Inject

class DeleteContactUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    suspend operator fun invoke(contactId: Long) {
        repository.deleteContact(contactId)
    }
}