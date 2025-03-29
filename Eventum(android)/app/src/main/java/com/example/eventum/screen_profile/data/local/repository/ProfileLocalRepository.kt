package com.example.eventum.screen_profile.data.local.repository

import com.example.eventum.data.local.entity.UserEntity

interface ProfileLocalRepository {
    suspend fun updateUser(user: UserEntity): Boolean
}