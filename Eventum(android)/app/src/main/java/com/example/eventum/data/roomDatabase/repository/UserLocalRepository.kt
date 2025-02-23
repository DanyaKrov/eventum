package com.example.eventum.data.roomDatabase.repository

import com.example.eventum.data.roomDatabase.dao.UserDao
import com.example.eventum.data.roomDatabase.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalRepository @Inject constructor(private val userDao: UserDao) {
    suspend fun insertUser(user: UserEntity) = userDao.insert(user)
    suspend fun getUser(): UserEntity = userDao.get()
}