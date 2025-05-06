package com.example.eventum.screen_profile.domain.useCase

import com.example.eventum.domain.model.Operation
import com.example.eventum.domain.model.User
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.domain.repository.ContactsRepository
import com.example.eventum.screen_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(user: User): Flow<Operation> = flow{
        try {
            emit(Operation.Loading())
            repository.updateUser(user)
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