package com.example.eventum.data.roomDatabase.app

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.eventum.data.roomDatabase.converter.ListConverter
import com.example.eventum.data.roomDatabase.dao.EventDao
import com.example.eventum.data.roomDatabase.dao.PresentDao
import com.example.eventum.data.roomDatabase.dao.UserDao
import com.example.eventum.data.roomDatabase.entity.EventEntity
import com.example.eventum.data.roomDatabase.entity.PresentEntity
import com.example.eventum.data.roomDatabase.entity.UserEntity
import com.example.eventum.data.roomDatabase.entity.WishListEntity

@Database(entities = [UserEntity::class, EventEntity::class, PresentEntity::class,
                     WishListEntity::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun eventDao(): EventDao
    abstract fun presentDao(): PresentDao
}