package com.example.eventum.screen_event.domain.useCase

import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.domain.repository.EventRepository
import com.example.eventum.screen_mainPage.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CreateNotificationUseCase @Inject constructor(
    private val repository: EventRepository
) {
    operator fun invoke(notification: NotificationModel):
            Flow<Resource<NotificationModel>> = flow {
                try {
                    emit(Resource.Loading())
                    val requestId = repository.addNotification(notification)
                    val createdNotification = NotificationModel(
                        notification.id,
                        requestId,
                        notification.title,
                        notification.description,
                        notification.time,
                        notification.eventOwnerId
                    )
                    emit(Resource.Success(createdNotification))
                }
                catch (e: IOException) {
                    emit(Resource.Error("Couldn't organise notification"))
                }
                catch (e: Exception) {
                    emit(Resource.Error("Unexpected error occurred"))
                }
    }
}