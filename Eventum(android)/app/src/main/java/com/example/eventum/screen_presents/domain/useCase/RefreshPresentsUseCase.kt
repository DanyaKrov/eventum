package com.example.eventum.screen_presents.domain.useCase

import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.repository.EventsRepository
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_presents.domain.repository.PresentsRepository
import com.example.eventum.util.mapper.EventMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import java.io.IOException
import javax.inject.Inject

class RefreshPresentsUseCase @Inject constructor(
    private val repository: PresentsRepository
) {
    operator fun invoke(wishListId: Long, refreshLocalDatabase: Boolean): Flow<Resource<MutableList<Present>>> =
        flow{
            try {
                emit(Resource.Loading())
                val contacts = repository.getPresents(wishListId, refreshLocalDatabase).toMutableList()
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