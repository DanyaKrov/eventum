package com.example.eventum.app

import android.app.Application
import android.content.Context
import com.example.eventum.data.api.RetrofitClient
import com.example.eventum.data.roomDatabase.mapper.UserMapper
import com.example.eventum.login.data.remote.api.LoginApiService
import com.example.eventum.login.data.remote.repository.LoginApiRepository
import com.example.eventum.login.domain.repository.LoginRepository
import com.example.eventum.mainPage.data.remote.dataSource.EventsRemoteDataSource
import com.example.eventum.mainPage.data.remote.service.EventsRemoteService
import com.example.eventum.mainPage.data.remote.repository.EventsRemoteRepository
import com.example.eventum.signUp.data.api.SignUpRepository
import com.example.eventum.util.StringRepository
import com.example.eventum.util.mapper.EventMapper
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
}