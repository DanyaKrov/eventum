package com.example.eventum.screen_contacts.domain.useCase

import com.example.eventum.domain.model.Operation
import com.example.eventum.screen_contacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DeleteContactUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    suspend operator fun invoke(contactId: Long): Flow<Operation> = flow{
        try {
            emit(Operation.Loading())
            repository.deleteContact(contactId)
            emit(Operation.Success())
        }
        catch (_: IOException) {
            emit(Operation.Error(message = "Couldn't reach server"))
        }
        catch (_: Exception) {
            emit(Operation.Error(message = "Unexpected error occurred"))

        }
    }
}