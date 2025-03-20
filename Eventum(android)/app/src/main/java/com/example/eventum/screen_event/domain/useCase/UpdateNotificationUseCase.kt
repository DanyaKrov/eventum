package com.example.eventum.screen_event.domain.useCase

import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.domain.repository.EventRepository
import javax.inject.Inject

class UpdateNotificationUseCase @Inject constructor(
    private val repository: EventRepository
) {
    suspend operator fun invoke(newNotification: NotificationModel) {
        repository.changeNotification(newNotification)
    }
}