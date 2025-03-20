package com.example.eventum.screen_signUp.data

import com.example.eventum.screen_contacts.data.local.repository.ContactsLocalRepository
import com.example.eventum.screen_contacts.data.local.service.ContactsLocalService
import com.example.eventum.screen_signUp.data.remote.repository.SignUpRemoteRepository
import com.example.eventum.screen_signUp.data.remote.service.SignUpRemoteService
import com.example.eventum.screen_signUp.data.service.SignUpService
import com.example.eventum.screen_signUp.domain.repository.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SignUpHiltModule{
    @Binds
    @Singleton
    abstract fun bindSignUpRemoteRepository(
        impl: SignUpRemoteService
    ): SignUpRemoteRepository

    @Binds
    @Singleton
    abstract fun bindSignUpRepository(
        impl: SignUpService
    ): SignUpRepository
}