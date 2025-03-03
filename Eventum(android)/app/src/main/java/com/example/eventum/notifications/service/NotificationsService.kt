package com.example.eventum.notifications.service

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.eventum.common.Constants
import com.example.eventum.notifications.model.Notification
import com.example.eventum.notifications.repository.NotificationsRepository
import com.example.eventum.notifications.worker.NotificationWorker
import java.time.Duration
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Calendar
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NotificationsService @Inject constructor(
    private val context: Context
): NotificationsRepository {
    override fun create(notification: Notification): String = createNotification(notification).toString()

    override fun delete(requestId: String): String {
        return try {
            WorkManager.getInstance(context).cancelWorkById(UUID.fromString(requestId))
            "Canceled with success"
        } catch (e: Exception) {
            "Error occurred while canceling request and notification"
        }
    }

    override fun deleteAll(eventId: Long): String {
        return try {
            WorkManager.getInstance(context).cancelAllWorkByTag(eventId.toString())
            "Canceled with success"
        } catch (e: Exception) {
            "Error occurred while canceling request and notification"
        }
    }

    override fun update(requestId: String, notification: Notification): String {
        return try {
            WorkManager.getInstance(context).cancelWorkById(UUID.fromString(requestId)) // cancel old request
            createNotification(notification).toString() // create new one and return new requestId
        } catch (e: Exception) {
            "Error occurred while updating request and notification"
        }
    }


    private fun createNotification(notification: Notification): UUID  {
        val inputData = Data.Builder()
            .putInt(Constants.NOTIFICATION_ID, notification.id)
            .putString(Constants.NOTIFICATION_TITlE, notification.title)
            .putString(Constants.NOTIFICATION_DESCRIPTION, notification.description)
            .build()

        val request = OneTimeWorkRequestBuilder<NotificationWorker>()
            .addTag(notification.eventId.toString()) // event id as tag of work requests - notifications
            .setInputData(inputData)
            .setInitialDelay(calculateTime(notification.time), TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(context).enqueue(request)
        return request.id
    }


    private fun calculateTime(targetDate: LocalDate): Long {
        val now = ZonedDateTime.now(ZoneId.systemDefault())
        val notificationTime = targetDate.atTime(12, 0) // average time. Can be changed from settings
            .atZone(ZoneId.systemDefault())


        val finalTime = if (notificationTime.isBefore(now)) {
            notificationTime.plusDays(1)
        } else {
            notificationTime
        }

        return TimeUnit.HOURS.toHours(
            Duration.between(now, finalTime).toMillis()
        )
    }
}