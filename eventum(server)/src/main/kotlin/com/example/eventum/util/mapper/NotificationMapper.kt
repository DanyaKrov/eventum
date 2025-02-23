package com.example.eventum.util.mapper

import com.example.eventum.database.entity.Event
import com.example.eventum.database.entity.Notification
import com.example.eventum.model.request.EventRequest
import com.example.eventum.model.request.NotificationRequest
import com.example.eventum.model.response.EventResponse
import com.example.eventum.model.response.NotificationResponse
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class NotificationMapper {
    val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE // formatter for input date, format: yyyy-MM-dd

    fun entityToResponse(notification: Notification): NotificationResponse {
        return NotificationResponse(notification.id,
            notification.name,
            notification.description,
            notification.time.toString(),
            notification.event.id)
            }

    fun updateNotification(notification: Notification, newNotification: NotificationRequest): Notification = notification.apply {
        notification.name = newNotification.name
        notification.description = newNotification.description
        notification.time = LocalDate.parse(newNotification.time, dateFormatter) // when updating, service doesn't need to change event
    }

    fun createNotification(notification: NotificationRequest, event: Event): Notification = Notification(
        name =notification.name,
        description =notification.description,
        time = LocalDate.parse(notification.time, dateFormatter),
        event = event
    )
}