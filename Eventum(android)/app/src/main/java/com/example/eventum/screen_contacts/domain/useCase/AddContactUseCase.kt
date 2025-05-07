package com.example.eventum.screen_contacts.domain.useCase


import android.util.Log
import com.example.eventum.domain.model.Operation
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.domain.model.ContactRequestModel
import com.example.eventum.screen_contacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AddContactUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    operator fun invoke(userId: Long, contact: ContactRequestModel): Flow<Operation> = flow{
        try {
            emit(Operation.Loading())
            repository.createContact(userId, contact)
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