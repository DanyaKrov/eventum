package com.example.eventum.screen_event.presentation.event

import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_mainPage.domain.model.Event

sealed class EventPageEvent {
    class AddContact(val contact: Contact): EventPageEvent()
    class CreateNotification(val notification: NotificationModel): EventPageEvent()
    class DeleteNotification(val notification: NotificationModel): EventPageEvent()
    class EditEvent(val event: Event): EventPageEvent()
}