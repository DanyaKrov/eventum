package com.example.eventum.screen_event.domain.repository

import com.example.eventum.domain.model.User
import com.example.eventum.feature_notifications.model.Notification
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_mainPage.domain.model.Event

interface EventRepository {
    suspend fun getEvent(remoteId: Long): Event
    suspend fun updateEvent(newEvent: Event): Boolean
    suspend fun changeUsersList(newUsers: List<User>): Boolean
    suspend fun getEventNotifications(event: Event): List<NotificationModel>
    suspend fun changeNotification(newNotification: NotificationModel): String // will return new request id
    suspend fun deleteNotification(notification: NotificationModel): Boolean
    suspend fun addNotification(notification: NotificationModel): String // will return request id

}