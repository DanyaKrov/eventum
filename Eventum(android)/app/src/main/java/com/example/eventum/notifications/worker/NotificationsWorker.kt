package com.example.eventum.notifications.worker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ClipDescription
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.eventum.common.Constants
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class NotificationWorker (
    private val context: Context,
    private val workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val notificationId = inputData.getInt(Constants.NOTIFICATION_ID, 0) // get parameters of notification
        val notificationTitle = inputData.getString(Constants.NOTIFICATION_TITlE)
        val notificationDescription = inputData.getString(Constants.NOTIFICATION_DESCRIPTION)
        showNotification(notificationId, notificationTitle, notificationDescription)
        return Result.success()
    }

    private fun showNotification(notificationId: Int, title: String?, description: String?) {
        val channelId = "events_notifications_channel"

        val channel = NotificationChannel(
            channelId, "Ежедневные уведомления", NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title ?: Constants.NOTIFICATION_DEFAULT_TITLE)
            .setContentText(description ?: Constants.NOTIFICATION_DEFAULT_DESCRIPTION)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        if (ActivityCompat.checkSelfPermission( // check for permission
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(context).notify(notificationId, notification)
    }
}