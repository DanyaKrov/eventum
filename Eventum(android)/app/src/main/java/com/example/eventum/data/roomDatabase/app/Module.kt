package com.example.eventum.data.roomDatabase.app

import android.content.Context
import androidx.room.Room
import com.example.eventum.data.roomDatabase.dao.EventDao
import com.example.eventum.data.roomDatabase.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "eventum_local_database"
        ).build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    fun provideEventDao(db: AppDatabase): EventDao {
        return db.eventDao()
    }
}