package com.example.eventum.login.data.remote.api

import com.example.eventum.data.api.model.UserResponse
import com.example.eventum.login.domain.model.AuthRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("auth/login")
    suspend fun authorise(@Body authRequest: AuthRequest): Response<UserResponse>
}