package com.example.eventum.screen_profile.data.local.service

import com.example.eventum.data.local.dao.UserDao
import com.example.eventum.data.local.entity.UserEntity
import com.example.eventum.screen_profile.data.local.repository.ProfileLocalRepository
import javax.inject.Inject

class ProfileLocalService @Inject constructor(
    private val dao: UserDao
): ProfileLocalRepository {
    override suspend fun updateUser(user: UserEntity): Boolean {
        return try {
            dao.update(user)
            true
        }
        catch (e: Exception) {
            false
        }
    }
}