package com.example.eventum.screen_login.data

import com.example.eventum.screen_login.data.remote.repository.LoginRemoteRepository
import com.example.eventum.screen_login.data.remote.service.LoginRemoteService
import com.example.eventum.screen_login.data.service.LoginService
import com.example.eventum.screen_login.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginHiltModule {
    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        impl: LoginService
    ): LoginRepository


    @Binds
    @Singleton
    abstract fun bindLoginRemoteRepository(
        impl: LoginRemoteService
    ): LoginRemoteRepository
}