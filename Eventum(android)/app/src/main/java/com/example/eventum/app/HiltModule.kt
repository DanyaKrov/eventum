package com.example.eventum.app

import android.app.Application
import android.content.Context
import com.example.eventum.api.RetrofitClient
import com.example.eventum.login.api.LoginRepository
import com.example.eventum.mainPage.api.EventsRepository
import com.example.eventum.signUp.api.SignUpRepository
import com.example.eventum.util.StringRepository
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
    fun provideLoginRepository(): LoginRepository {
        return RetrofitClient.createLoginInstance()
    }

    @Provides
    @Singleton
    fun provideEventsRepository(): EventsRepository {
        return RetrofitClient.createEventsInstance()
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}