package com.example.eventum.screen_mainPage.data

import com.example.eventum.screen_mainPage.data.local.repository.EventsLocalRepository
import com.example.eventum.screen_mainPage.data.local.service.EventsLocalService
import com.example.eventum.screen_mainPage.data.remote.repository.EventsRemoteRepository
import com.example.eventum.screen_mainPage.data.remote.service.EventsRemoteService
import com.example.eventum.screen_mainPage.data.service.EventsService
import com.example.eventum.screen_mainPage.domain.repository.EventsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MainPageHiltModule {
    @Binds
    @Singleton
    abstract fun bindEventsRemoteRepository(
        impl: EventsRemoteService
    ): EventsRemoteRepository


    @Binds
    @Singleton
    abstract fun bindEventsLocalRepository(
        impl: EventsLocalService
    ): EventsLocalRepository

    @Binds
    @Singleton
    abstract fun bindEventsRepository(
        impl: EventsService
    ): EventsRepository
}