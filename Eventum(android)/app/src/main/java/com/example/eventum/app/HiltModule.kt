package com.example.eventum.app

import android.app.Application
import android.content.Context
import com.example.eventum.data.api.RetrofitClient
import com.example.eventum.data.roomDatabase.mapper.UserMapper
import com.example.eventum.screen_login.data.remote.api.LoginApiService
import com.example.eventum.screen_login.data.remote.repository.LoginApiRepository
import com.example.eventum.screen_login.domain.repository.LoginRepository
import com.example.eventum.screen_mainPage.data.remote.dataSource.EventsRemoteDataSource
import com.example.eventum.notifications.repository.NotificationsRepository
import com.example.eventum.notifications.service.NotificationsService
import com.example.eventum.screen_presents.data.remote.datasource.PresentsRemoteDataSource
import com.example.eventum.screen_presents.data.remote.datasource.WishListRemoteDataSource
import com.example.eventum.screen_signUp.data.api.SignUpRepository
import com.example.eventum.util.StringRepository
import com.example.eventum.util.mapper.EventMapper
import com.example.eventum.util.mapper.PresentMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideSignUpRepository(): SignUpRepository {
        return RetrofitClient.createSignUpInstance()
    }

    @Provides
    @Singleton
    fun provideLoginRepository(): LoginApiService {
        return RetrofitClient.createLoginInstance()
    }

    @Provides
    @Singleton
    fun providePresentsRemoteDataSource(): PresentsRemoteDataSource {
        return RetrofitClient.createPresentsInstance()
    }


    @Provides
    @Singleton
    fun provideWishListRemoteDataSource(): WishListRemoteDataSource {
        return RetrofitClient.createWishListRemoteDataSource()
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
}


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryHiltModule { // domain repositories implementations for UseCase classes
    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        impl: LoginApiRepository
    ): LoginRepository


    @Binds
    @Singleton
    abstract fun bindNotificationsRepository(
        impl: NotificationsService
    ): NotificationsRepository
}