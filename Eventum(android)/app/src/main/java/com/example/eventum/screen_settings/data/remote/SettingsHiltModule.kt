package com.example.eventum.screen_settings.data.remote

import com.example.eventum.screen_presents.data.local.repository.PresentsLocalRepository
import com.example.eventum.screen_presents.data.local.service.PresentsLocalService
import com.example.eventum.screen_presents.data.remote.repository.PresentsRemoteRepository
import com.example.eventum.screen_presents.data.remote.service.PresentsRemoteService
import com.example.eventum.screen_presents.data.service.PresentsService
import com.example.eventum.screen_presents.domain.repository.PresentsRepository
import com.example.eventum.screen_settings.data.remote.repository.SettingsRemoteRepository
import com.example.eventum.screen_settings.data.remote.service.SettingsRemoteService
import com.example.eventum.screen_settings.data.service.SettingsService
import com.example.eventum.screen_settings.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsHiltModule {
    @Binds
    @Singleton
    abstract fun bindSettingsRemoteRepository(
        impl: SettingsRemoteService
    ): SettingsRemoteRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(
        impl: SettingsService
    ): SettingsRepository
}