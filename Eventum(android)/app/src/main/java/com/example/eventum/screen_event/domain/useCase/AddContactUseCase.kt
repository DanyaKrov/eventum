package com.example.eventum.screen_event.domain.useCase

import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.domain.repository.EventRepository
import com.example.eventum.screen_mainPage.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AddContactUseCase @Inject constructor(
    private val repository: EventRepository
) {
    operator fun invoke(notification: NotificationModel):
            Flow<Resource<NotificationModel>> = flow {
              // I need to add contacts for testing it
    }
}