package com.example.eventum.screen_event.data.local.service

import com.example.eventum.data.local.dao.NotificationDao
import com.example.eventum.data.local.entity.NotificationEntity
import com.example.eventum.screen_event.data.local.repository.NotificationsLocalRepository
import javax.inject.Inject

class NotificationsLocalService @Inject constructor(
    private val dao: NotificationDao
): NotificationsLocalRepository {
    override suspend fun getAll(eventId: Long): List<NotificationEntity> = dao.getAll(eventId)

    override suspend fun create(notification: NotificationEntity) = dao.insert(notification)

    override suspend fun update(notification: NotificationEntity): Boolean {
        return try {
            dao.update(notification)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun delete(notificationId: Long): Boolean {
        return try {
            dao.delete(notificationId)
            true
        }
        catch (e: Exception) {
            false
        }
    }
}