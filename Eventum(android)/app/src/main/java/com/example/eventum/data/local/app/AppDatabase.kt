package com.example.eventum.data.local.app

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eventum.data.local.converter.ListConverter
import com.example.eventum.data.local.dao.ContactDao
import com.example.eventum.data.local.dao.EventDao
import com.example.eventum.data.local.dao.NotificationDao
import com.example.eventum.data.local.dao.PresentDao
import com.example.eventum.data.local.dao.UserDao
import com.example.eventum.data.local.dao.WishListDao
import com.example.eventum.data.local.entity.ContactEntity
import com.example.eventum.data.local.entity.EventEntity
import com.example.eventum.data.local.entity.NotificationEntity
import com.example.eventum.data.local.entity.PresentEntity
import com.example.eventum.data.local.entity.UserEntity
import com.example.eventum.data.local.entity.WishListEntity

@Database(entities = [UserEntity::class, EventEntity::class, PresentEntity::class,
                     WishListEntity::class, ContactEntity::class,
                     NotificationEntity::class],version = 1)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun eventDao(): EventDao
    abstract fun presentDao(): PresentDao
    abstract fun contactDao(): ContactDao
    abstract fun wishListDao(): WishListDao
    abstract fun notificationDao(): NotificationDao
}