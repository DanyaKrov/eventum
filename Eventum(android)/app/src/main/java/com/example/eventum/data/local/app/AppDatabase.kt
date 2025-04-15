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
import com.example.eventum.data.local.model.entity.ContactEntity
import com.example.eventum.data.local.model.entity.EventContactsCrossRef
import com.example.eventum.data.local.model.entity.EventEntity
import com.example.eventum.data.local.model.entity.GiftEntity
import com.example.eventum.data.local.model.entity.GiftStateEntity
import com.example.eventum.data.local.model.entity.NotificationEntity
import com.example.eventum.data.local.model.entity.PresentEntity
import com.example.eventum.data.local.model.entity.UserEntity
import com.example.eventum.data.local.model.entity.UserCrossUserRef
import com.example.eventum.data.local.model.entity.WishListEntity
import com.example.eventum.screen_giftList.data.local.dao.GiftDao

@Database(entities = [UserEntity::class, EventEntity::class, PresentEntity::class,
    WishListEntity::class, ContactEntity::class, GiftStateEntity::class,
    NotificationEntity::class, GiftEntity::class, UserCrossUserRef::class,
    EventContactsCrossRef::class
                     ],version = 1)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun eventDao(): EventDao
    abstract fun presentDao(): PresentDao
    abstract fun contactDao(): ContactDao
    abstract fun wishListDao(): WishListDao
    abstract fun notificationDao(): NotificationDao
    abstract fun giftDao(): GiftDao
}