package com.example.eventum.notifications.repository

import com.example.eventum.notifications.model.Notification

interface NotificationsRepository {
    fun create(notification: Notification): String // return request id in order to cancel request if needed
    fun delete(requestId: String): String
    fun deleteAll(eventId: Long): String
    fun update(requestId: String, notification: Notification): String
}