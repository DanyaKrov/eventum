package com.example.eventum.service

import com.example.eventum.database.entity.Notification
import com.example.eventum.model.request.NotificationRequest
import com.example.eventum.model.response.NotificationResponse
import org.springframework.stereotype.Service

@Service
interface NotificationService {
    fun getById(id: Long): Notification
    fun getAll(): List<NotificationResponse>
    fun update(id: Long, newNotification: NotificationRequest): NotificationResponse
    fun delete(id: Long): String
    fun create(notification: NotificationRequest, eventId: Long): NotificationResponse
}