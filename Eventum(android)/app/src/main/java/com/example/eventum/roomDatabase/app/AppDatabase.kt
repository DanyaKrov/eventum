package com.example.eventum.roomDatabase.app

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.eventum.roomDatabase.dao.UserDao
import com.example.eventum.roomDatabase.entity.UserEntity
import com.example.eventum.roomDatabase.entity.UserFriendCrossRef
import com.example.eventum.roomDatabase.repository.UserRepository
import java.util.concurrent.locks.ReentrantLock

@Database(entities = [UserEntity::class, UserFriendCrossRef::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}