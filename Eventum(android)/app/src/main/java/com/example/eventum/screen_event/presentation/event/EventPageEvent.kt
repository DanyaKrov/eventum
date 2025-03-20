package com.example.eventum.screen_event.presentation.event

import com.example.eventum.screen_event.domain.model.NotificationModel

sealed class EventPageEvent {
    class AddUserEvent(): EventPageEvent()
    class EditNotification(val notification: NotificationModel): EventPageEvent()
    class DeleteNotification(val notification: NotificationModel): EventPageEvent()
    class EditEvent(): EventPageEvent()
}