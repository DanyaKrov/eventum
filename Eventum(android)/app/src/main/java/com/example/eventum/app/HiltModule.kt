package com.example.eventum.app

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.eventum.data.local.preferences.EventPreferences
import com.example.eventum.data.remote.RetrofitClient
import com.example.eventum.data.local.preferences.UserPreferences
import com.example.eventum.data.local.preferences.WishListPreferences
import com.example.eventum.screen_mainPage.data.remote.dataSource.EventsRemoteDataSource
import com.example.eventum.feature_notifications.repository.NotificationsRepository
import com.example.eventum.feature_notifications.service.NotificationsService
import com.example.eventum.screen_contacts.data.remote.dataSource.ContactsRemoteDataSource
import com.example.eventum.screen_login.data.remote.dataSource.LoginRemoteDataSource
import com.example.eventum.screen_presents.data.remote.dataSource.PresentsRemoteDataSource
import com.example.eventum.screen_profile.data.remote.dataSource.ProfileRemoteDataSource
import com.example.eventum.screen_signUp.data.remote.dataSource.UsersRemoteDataSource
import com.example.eventum.screen_wishList.data.remote.dataSource.WishListRemoteDataSource
import com.example.eventum.util.reader.StringRepository
import com.example.eventum.util.mapper.ContactMapper
import com.example.eventum.util.mapper.EventMapper
import com.example.eventum.util.mapper.NotificationMapper
import com.example.eventum.util.mapper.PresentMapper
import com.example.eventum.util.mapper.UserMapper
import com.example.eventum.util.mapper.WishListMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HiltModule { // dependency injection module for using StringRepository class
    @Provides
    @Singleton
    fun provideSpringRepository(context: Context): StringRepository {
        return StringRepository(context)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(): LoginRemoteDataSource {
        return RetrofitClient.createLoginInstance()
    }

    @Provides
    @Singleton
    fun providePresentsRemoteDataSource(): PresentsRemoteDataSource {
        return RetrofitClient.createPresentsInstance()
    }

    @Provides
    @Singleton
    fun provideProfileRemoteDataSource(): ProfileRemoteDataSource {
        return RetrofitClient.createProfileInstance()
    }


    @Provides
    @Singleton
    fun provideUsersRemoteDataSource(): UsersRemoteDataSource {
        return RetrofitClient.createUsersInstance()
    }


    @Provides
    @Singleton
    fun provideWishListRemoteDataSource(): WishListRemoteDataSource {
        return RetrofitClient.createWishListRemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideContactsRemoteDataSource(): ContactsRemoteDataSource {
        return RetrofitClient.createContactsRemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideEventsRepository(): EventsRemoteDataSource {
        return RetrofitClient.createEventsInstance()
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }


    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_prefs")
    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Provides
    @Singleton
    fun provideEventPreferences(@ApplicationContext context: Context): EventPreferences {
        return EventPreferences(context)
    }

    @Provides
    @Singleton
    fun provideWishListPreferences(@ApplicationContext context: Context): WishListPreferences {
        return WishListPreferences(context)
    }

    @Provides
    @Singleton
    fun provideEventMapper(): EventMapper {
        return EventMapper()
    }

    @Provides
    @Singleton
    fun providePresentMapper(): PresentMapper {
        return PresentMapper()
    }

    @Provides
    @Singleton
    fun provideUserMapper(): UserMapper {
        return UserMapper()
    }

    @Provides
    @Singleton
    fun provideWishListMapper(): WishListMapper {
        return WishListMapper()
    }

    @Provides
    @Singleton
    fun provideContactMapper(): ContactMapper {
        return ContactMapper()
    }

    @Provides
    @Singleton
    fun provideNotificationMapper(): NotificationMapper {
        return NotificationMapper()
    }
}


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryHiltModule { // domain repositories implementations for UseCase classes
    @Binds
    @Singleton
    abstract fun bindNotificationsRepository(
        impl: NotificationsService
    ): NotificationsRepository
}