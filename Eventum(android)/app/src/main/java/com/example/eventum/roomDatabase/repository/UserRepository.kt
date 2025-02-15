package com.example.eventum.roomDatabase.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.eventum.roomDatabase.dao.UserDao
import com.example.eventum.roomDatabase.entity.UserEntity
import com.example.eventum.roomDatabase.entity.UserFriendCrossRef
import com.example.eventum.roomDatabase.entity.UserFriends
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao) {
    suspend fun insertUser(user: UserEntity) = userDao.insert(user)
    suspend fun getUsers(id: Long): UserEntity = userDao.getUser(id)
}