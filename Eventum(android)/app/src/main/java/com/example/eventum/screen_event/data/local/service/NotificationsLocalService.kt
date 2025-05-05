package com.example.eventum.screen_event.data.local.service

import android.util.Log
import com.example.eventum.data.local.dao.NotificationDao
import com.example.eventum.data.local.model.entity.NotificationEntity
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

    override suspend fun delete(requestId: String): Boolean {
        return try {
            dao.delete(requestId)
            true
        }
        catch (e: Exception) {
            false
        }
    }
}