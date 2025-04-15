package com.example.eventum.screen_event.data.local.repository

import com.example.eventum.data.local.model.entity.NotificationEntity

interface NotificationsLocalRepository {
    suspend fun getAll(eventId: Long): List<NotificationEntity>
    suspend fun create(notification: NotificationEntity)
    suspend fun update(notification: NotificationEntity): Boolean
    suspend fun delete(notificationId: Long): Boolean
}