package com.example.eventum.util.mapper
import com.example.eventum.data.local.entity.NotificationEntity
import com.example.eventum.feature_notifications.model.Notification
import com.example.eventum.screen_event.domain.model.NotificationModel
import dagger.internal.DaggerGenerated
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@DaggerGenerated
class NotificationMapper {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun fromModelToEntity(notification: NotificationModel, requestId: String): NotificationEntity =
        NotificationEntity(
            requestId=requestId,
            title=notification.title,
            description=notification.description,
            time=notification.time,
            eventOwnerId=notification.eventOwnerId
        )

    fun fromModelToWork(notification: NotificationModel): Notification = Notification(
        notification.id,
        notification.title,
        notification.description,
        LocalDate.parse(notification.time, formatter),
        notification.eventOwnerId
    ) // from model to format needed for setting notification in system


    fun fromEntityToModel(notification: NotificationEntity): NotificationModel =
        NotificationModel(
            requestId=notification.requestId,
            title=notification.title,
            description=notification.description,
            time=notification.time,
            eventOwnerId = notification.eventOwnerId
        )
}