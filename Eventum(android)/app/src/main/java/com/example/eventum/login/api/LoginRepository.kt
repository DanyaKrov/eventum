package com.example.eventum.login.api

import com.example.eventum.login.api.model.AuthRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRepository {
    @POST("auth/login")
    suspend fun authotirise(@Body authRequest: AuthRequest): Boolean
}