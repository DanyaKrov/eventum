package com.example.eventum.screen_profile.data

import com.example.eventum.screen_mainPage.data.local.repository.EventsLocalRepository
import com.example.eventum.screen_mainPage.data.local.service.EventsLocalService
import com.example.eventum.screen_mainPage.data.service.EventsService
import com.example.eventum.screen_mainPage.domain.repository.EventsRepository
import com.example.eventum.screen_profile.data.local.repository.ProfileLocalRepository
import com.example.eventum.screen_profile.data.local.service.ProfileLocalService
import com.example.eventum.screen_profile.data.remote.repository.ProfileRemoteRepository
import com.example.eventum.screen_profile.data.remote.service.ProfileRemoteService
import com.example.eventum.screen_profile.data.service.ProfileService
import com.example.eventum.screen_profile.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileHiltModule{
    @Binds
    @Singleton
    abstract fun bindProfileRemoteRepository(
        impl: ProfileRemoteService
    ): ProfileRemoteRepository


    @Binds
    @Singleton
    abstract fun bindProfileLocalRepository(
        impl: ProfileLocalService
    ): ProfileLocalRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        impl: ProfileService
    ): ProfileRepository
}