package com.example.eventum.screen_event.domain.useCase

import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.domain.model.ContactsModel
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.domain.repository.EventRepository
import com.example.eventum.screen_mainPage.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetEventContactsUseCase @Inject constructor(
    private val repository: EventRepository
) {
    operator fun invoke(event: Event): Flow<Resource<MutableList<Contact>>> = flow {
        try {
            emit(Resource.Loading())
            val contacts = repository.getEventContacts(event).toMutableList()
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