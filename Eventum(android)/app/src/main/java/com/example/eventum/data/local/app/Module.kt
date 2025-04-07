package com.example.eventum.data.local.app

import android.content.Context
import androidx.room.Room
import com.example.eventum.data.local.dao.ContactDao
import com.example.eventum.data.local.dao.EventDao
import com.example.eventum.data.local.dao.NotificationDao
import com.example.eventum.data.local.dao.PresentDao
import com.example.eventum.data.local.dao.UserDao
import com.example.eventum.data.local.dao.WishListDao
import com.example.eventum.screen_giftList.data.local.dao.GiftDao
import com.example.eventum.screen_giftList.data.local.dao.GiftListDao
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

    @Provides
    fun providePresentDao(db: AppDatabase): PresentDao {
        return db.presentDao()
    }

    @Provides
    fun provideContactDao(db: AppDatabase): ContactDao {
        return db.contactDao()
    }

    @Provides
    fun provideWishListDao(db: AppDatabase): WishListDao {
        return db.wishListDao()
    }

    @Provides
    fun provideNotificationDao(db: AppDatabase): NotificationDao {
        return db.notificationDao()
    }


    @Provides
    fun provideGiftDao(db: AppDatabase): GiftDao {
        return db.giftDao()
    }

    @Provides
    fun provideGiftListDao(db: AppDatabase): GiftListDao {
        return db.giftListDao()
    }
}