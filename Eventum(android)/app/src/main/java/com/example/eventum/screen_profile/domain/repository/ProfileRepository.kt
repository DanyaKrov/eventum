package com.example.eventum.screen_profile.domain.repository

import com.example.eventum.domain.model.User

interface ProfileRepository {
    suspend fun updateUser(user: User): Boolean
}