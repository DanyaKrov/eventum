package com.example.eventum.data.local.repository

import com.example.eventum.data.local.dao.UserDao
import com.example.eventum.data.local.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalRepository @Inject constructor(private val userDao: UserDao) {
    suspend fun insertUser(user: UserEntity) = userDao.insert(user)
    suspend fun getUser(): UserEntity = userDao.get()
}