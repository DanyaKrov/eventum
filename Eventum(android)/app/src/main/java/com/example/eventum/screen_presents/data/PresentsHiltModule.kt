package com.example.eventum.screen_presents.data

import com.example.eventum.screen_mainPage.data.local.repository.EventsLocalRepository
import com.example.eventum.screen_mainPage.data.local.service.EventsLocalService
import com.example.eventum.screen_mainPage.data.remote.repository.EventsRemoteRepository
import com.example.eventum.screen_mainPage.data.remote.service.EventsRemoteService
import com.example.eventum.screen_mainPage.data.service.EventsService
import com.example.eventum.screen_mainPage.domain.repository.EventsRepository
import com.example.eventum.screen_presents.data.local.repository.PresentsLocalRepository
import com.example.eventum.screen_presents.data.local.service.PresentsLocalService
import com.example.eventum.screen_presents.data.remote.repository.PresentsRemoteRepository
import com.example.eventum.screen_presents.data.remote.service.PresentsRemoteService
import com.example.eventum.screen_presents.data.service.PresentsService
import com.example.eventum.screen_presents.domain.repository.PresentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PresentsHiltModule {
    @Binds
    @Singleton
    abstract fun bindPresentsLocalRepository(
        impl: PresentsLocalService
    ): PresentsLocalRepository


    @Binds
    @Singleton
    abstract fun bindPresentsRemoteRepository(
        impl: PresentsRemoteService
    ): PresentsRemoteRepository

    @Binds
    @Singleton
    abstract fun bindPresentsRepository(
        impl: PresentsService
    ): PresentsRepository
}