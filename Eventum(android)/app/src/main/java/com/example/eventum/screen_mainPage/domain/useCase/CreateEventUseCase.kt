package com.example.eventum.screen_mainPage.domain.useCase

import android.util.Log
import com.example.eventum.domain.model.DomainState
import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.model.EventRequestModel
import com.example.eventum.screen_mainPage.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CreateEventUseCase @Inject constructor(
    private val repository: EventsRepository
) {
    operator fun invoke(userRemoteId: Long, eventRequest: EventRequestModel):
            Flow<Resource<Event>> = flow {
        try {
            emit(Resource.Loading())
            val event = repository.createEvent(userRemoteId, eventRequest)
            emit(Resource.Success(event))
        }
        catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }
        catch (e: Exception) {
            emit(Resource.Error("Unexpected error occurred"))
        }
    }
}