package com.example.eventum.screen_event.data.service

import android.util.Log
import com.example.eventum.data.remote.model.response.ContactRemote
import com.example.eventum.domain.model.User
import com.example.eventum.feature_notifications.model.Notification
import com.example.eventum.feature_notifications.repository.NotificationsRepository
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.domain.repository.ContactsRepository
import com.example.eventum.screen_event.data.local.repository.EventLocalRepository
import com.example.eventum.screen_event.data.local.repository.NotificationsLocalRepository
import com.example.eventum.screen_event.data.remote.repository.EventRemoteRepository
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.domain.repository.EventRepository
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.util.mapper.ContactMapper
import com.example.eventum.util.mapper.EventMapper
import com.example.eventum.util.mapper.NotificationMapper
import javax.inject.Inject

class EventService @Inject constructor(
    private val localRepository: EventLocalRepository,
    private val remoteRepository: EventRemoteRepository,
    private val mapper: EventMapper,
    private val notificationMapper: NotificationMapper,
    private val contactMapper: ContactMapper,
    private val notificationsRepository: NotificationsRepository,
    private val notificationsLocalRepository: NotificationsLocalRepository,
    private val contactsRepository: ContactsRepository
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

    override suspend fun addContact(event: Event, contact: Contact): Boolean {
        return localRepository.addContact(event.remoteId, contact.remoteId) &&
                remoteRepository.addContact(event.remoteId, contact.remoteId)
    }

    override suspend fun removeContact(event: Event, contact: Contact): Boolean =
        localRepository.deleteContact(event.remoteId, contact.remoteId) &&
                remoteRepository.removeContact(event.remoteId, contact.remoteId)

    override suspend fun getEventContacts(event: Event): List<Contact> {
        val contacts = localRepository.getEventWithContacts(event.remoteId).contacts
        if (contacts.isEmpty()) {
            val remoteContacts = remoteRepository.getEventContacts(event.remoteId)
            remoteContacts.forEach {
                localRepository.addContact(event.remoteId, it.id)
            }
        }
        else
            return contacts.map {
                contactMapper.fromEntityToModel(it) }
        return localRepository.getEventWithContacts(event.remoteId).contacts.map {
            contactMapper.fromEntityToModel(it)
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
            notificationsLocalRepository.update(notificationMapper.fromModelToEntityRequest(
                newNotification, newRequestId))
            newRequestId
        }
        catch (e: Exception) {
            "Error occurred"
        }
    }

    override suspend fun deleteNotification(notification: NotificationModel):
            Boolean {
        return try {
            notificationsRepository.delete(notification.requestId)
            notificationsLocalRepository.delete(notification.requestId)
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