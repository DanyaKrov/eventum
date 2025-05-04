package com.example.eventum.screen_event.domain.useCase

import com.example.eventum.domain.model.Operation
import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_event.domain.repository.EventRepository
import com.example.eventum.screen_mainPage.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateEventUseCase @Inject constructor(
    private val repository: EventRepository
) {
    operator fun invoke(newEvent: Event): Flow<Operation> = flow{
        try {
            emit(Operation.Loading())
            repository.updateEvent(newEvent)
            emit(Operation.Success())
        }
        catch (e: IOException) {
            emit(Operation.Error("Couldn't reach server"))
        }
        catch (e: Exception) {
            emit(Operation.Error("Unexpected error occurred"))
        }
    }
}