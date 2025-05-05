package com.example.eventum.screen_event.domain.useCase

import com.example.eventum.domain.model.Operation
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateNotificationUseCase @Inject constructor(
    private val repository: EventRepository
) {
    operator fun invoke(newNotification: NotificationModel): Flow<Operation> = flow{
        try{
            emit(Operation.Loading())
            repository.changeNotification(newNotification)
            emit(Operation.Success())
        }
        catch (_: IOException) {
            emit(Operation.Error(message = "Couldn't reach the server"))
        }
        catch (_: Exception) {
            emit(Operation.Error(message = "Unexpected error occurred"))
        }
    }
}