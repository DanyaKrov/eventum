package com.example.eventum.screen_mainPage.domain.useCase

import android.util.Log
import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.repository.EventsRepository
import com.example.eventum.util.mapper.EventMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RefreshEventsUseCase @Inject constructor(
    private val repository: EventsRepository
) {
    suspend operator fun invoke(userId: Long, refreshLocalDatabase: Boolean): Flow<Resource<List<Event>>> =
        flow{
            try {
                emit(Resource.Loading())
                val events = repository.getEvents(userId, refreshLocalDatabase).toMutableList()
                emit(Resource.Success(events))
            }
            catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server"))
            }
            catch (e: Exception) {
                emit(Resource.Error("Unexpected error occurred"))
            }
        }
}