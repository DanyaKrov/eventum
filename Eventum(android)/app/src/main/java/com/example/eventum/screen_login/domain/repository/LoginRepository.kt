package com.example.eventum.screen_login.domain.repository

import com.example.eventum.data.remote.model.UserResponse
import com.example.eventum.screen_login.domain.model.AuthRequest

interface LoginRepository {
    suspend fun authorise(authRequest: AuthRequest): Result<UserResponse?>
}