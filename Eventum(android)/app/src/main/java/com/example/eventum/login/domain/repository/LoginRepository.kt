package com.example.eventum.login.domain.repository

import com.example.eventum.data.api.model.UserResponse
import com.example.eventum.login.domain.model.AuthRequest

interface LoginRepository {
    suspend fun authorise(authRequest: AuthRequest): Result<UserResponse?>
}