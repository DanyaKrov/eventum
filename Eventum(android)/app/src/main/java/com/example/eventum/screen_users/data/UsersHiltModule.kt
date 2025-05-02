package com.example.eventum.screen_users.data

import com.example.eventum.screen_signUp.data.remote.repository.SignUpRemoteRepository
import com.example.eventum.screen_signUp.data.remote.service.SignUpRemoteService
import com.example.eventum.screen_signUp.data.service.SignUpService
import com.example.eventum.screen_signUp.domain.repository.SignUpRepository
import com.example.eventum.screen_users.data.local.repository.UsersLocalRepository
import com.example.eventum.screen_users.data.local.service.UsersLocalService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UsersHiltModule{
    @Binds
    @Singleton
    abstract fun bindUsersLocalRepository(
        impl: UsersLocalService
    ): UsersLocalRepository
}