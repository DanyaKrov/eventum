package com.example.eventum.screen_login.data.remote.repository

import com.example.eventum.data.remote.model.response.UserRemote
import com.example.eventum.screen_login.domain.model.AuthRequest
import retrofit2.Response

interface LoginRemoteRepository {
    suspend fun authorise(authRequest: AuthRequest): Response<UserRemote>
}