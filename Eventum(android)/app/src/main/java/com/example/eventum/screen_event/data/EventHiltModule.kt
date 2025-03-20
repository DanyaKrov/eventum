package com.example.eventum.screen_event.data

import com.example.eventum.feature_notifications.repository.NotificationsRepository
import com.example.eventum.screen_contacts.data.local.repository.ContactsLocalRepository
import com.example.eventum.screen_contacts.data.local.service.ContactsLocalService
import com.example.eventum.screen_contacts.data.remote.repository.ContactsRemoteRepository
import com.example.eventum.screen_contacts.data.remote.service.ContactsRemoteService
import com.example.eventum.screen_contacts.data.service.ContactsService
import com.example.eventum.screen_contacts.domain.repository.ContactsRepository
import com.example.eventum.screen_event.data.local.repository.EventLocalRepository
import com.example.eventum.screen_event.data.local.repository.NotificationsLocalRepository
import com.example.eventum.screen_event.data.local.service.EventLocalService
import com.example.eventum.screen_event.data.local.service.NotificationsLocalService
import com.example.eventum.screen_event.data.remote.repository.EventRemoteRepository
import com.example.eventum.screen_event.data.remote.service.EventRemoteService
import com.example.eventum.screen_event.data.service.EventService
import com.example.eventum.screen_event.domain.repository.EventRepository
import com.example.eventum.screen_mainPage.data.service.EventsService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EventHiltModule {
    @Binds
    @Singleton
    abstract fun bindEventLocalRepository(
        impl: EventLocalService
    ): EventLocalRepository


    @Binds
    @Singleton
    abstract fun bindEventRemoteRepository(
        impl: EventRemoteService
    ): EventRemoteRepository

    @Binds
    @Singleton
    abstract fun bindEventRepository(
        impl: EventService
    ): EventRepository

    @Binds
    @Singleton
    abstract fun bindNotificationsLocalRepository(
        impl: NotificationsLocalService
    ): NotificationsLocalRepository
}