package com.example.eventum.screen_event.data.service

import com.example.eventum.domain.model.User
import com.example.eventum.feature_notifications.model.Notification
import com.example.eventum.feature_notifications.repository.NotificationsRepository
import com.example.eventum.screen_event.data.local.repository.EventLocalRepository
import com.example.eventum.screen_event.data.local.repository.NotificationsLocalRepository
import com.example.eventum.screen_event.data.remote.repository.EventRemoteRepository
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.domain.repository.EventRepository
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.util.mapper.EventMapper
import com.example.eventum.util.mapper.NotificationMapper
import javax.inject.Inject

class EventService @Inject constructor(
    private val localRepository: EventLocalRepository,
    private val remoteRepository: EventRemoteRepository,
    private val mapper: EventMapper,
    private val notificationMapper: NotificationMapper,
    private val notificationsRepository: NotificationsRepository,
    private val notificationsLocalRepository: NotificationsLocalRepository
): EventRepository {
    override suspend fun getEvent(remoteId: Long): Event {
        return try {
            mapper.entityToPresentableModel(localRepository.getEvent(remoteId))
        }
        catch (e: Exception) { // if event isn't in local database, he need to be created
            val event = remoteRepository.getEvent(remoteId)
            localRepository.createEvent(mapper.responseToEntity(event))
            mapper.entityToPresentableModel(localRepository.getEvent(remoteId))
        }
    }

    override suspend fun updateEvent(newEvent: Event): Boolean {
        return try {
            localRepository.updateEvent(mapper.updateEntity(newEvent))
            remoteRepository.update(newEvent.remoteId, mapper.modelToRequest(newEvent))
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun changeUsersList(newUsers: List<User>): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getEventNotifications(event: Event): List<NotificationModel> {
        val notifications = localRepository.getEventWithNotifications(event.remoteId).notifications
        return notifications.map { notificationMapper.fromEntityToModel(it) }
    }

    override suspend fun changeNotification(newNotification: NotificationModel): String {
        return try {
            val newRequestId = notificationsRepository.update(newNotification.requestId,
                notificationMapper.fromModelToWork(newNotification))
            notificationsLocalRepository.update(notificationMapper.fromModelToEntity(
                newNotification, newRequestId))
            newRequestId
        }
        catch (e: Exception) {
            "Error occurred"
        }
    }

    override suspend fun deleteNotification(notification: NotificationModel): Boolean {
        return try {
            notificationsRepository.delete(notification.requestId)
            notificationsLocalRepository.delete(notification.id)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun addNotification(notification: NotificationModel): String {
        return try {
            val requestId = notificationsRepository.create(notificationMapper.fromModelToWork(notification))
            notificationsLocalRepository.create(notificationMapper.fromModelToEntity(notification, requestId))
            requestId
        }
        catch (e: Exception) {
            "Error occurred"
        }
    }
}