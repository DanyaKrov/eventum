package com.example.eventum.screen_contacts.data

import com.example.eventum.screen_contacts.data.local.repository.ContactsLocalRepository
import com.example.eventum.screen_contacts.data.local.service.ContactsLocalService
import com.example.eventum.screen_contacts.data.remote.repository.ContactsRemoteRepository
import com.example.eventum.screen_contacts.data.remote.service.ContactsRemoteService
import com.example.eventum.screen_contacts.data.service.ContactsService
import com.example.eventum.screen_contacts.domain.repository.ContactsRepository
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
abstract class ContactsHiltModule {
    @Binds
    @Singleton
    abstract fun bindContactsLocalRepository(
        impl: ContactsLocalService
    ): ContactsLocalRepository


    @Binds
    @Singleton
    abstract fun bindContactsRemoteRepository(
        impl: ContactsRemoteService
    ): ContactsRemoteRepository

    @Binds
    @Singleton
    abstract fun bindContactsRepository(
        impl: ContactsService
    ): ContactsRepository
}