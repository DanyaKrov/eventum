package com.example.eventum.screen_contacts.domain.useCase

import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    operator fun invoke(userId: Long, refreshLocalDatabase: Boolean = false): Flow<Resource<MutableList<Contact>>> =
        flow{
            try {
                emit(Resource.Loading())
                val contacts = repository.getContacts(userId, refreshLocalDatabase).toMutableList()
                emit(Resource.Success(contacts))
            }
            catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server"))
            }
            catch (e: Exception) {
                emit(Resource.Error("Unexpected error occurred"))
            }
    }
}