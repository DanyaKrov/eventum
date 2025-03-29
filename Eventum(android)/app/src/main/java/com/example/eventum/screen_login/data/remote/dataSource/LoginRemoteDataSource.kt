package com.example.eventum.screen_login.data.remote.dataSource

import com.example.eventum.data.remote.model.UserResponse
import com.example.eventum.screen_login.domain.model.AuthRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRemoteDataSource {
    @POST("auth/login")
    suspend fun authorise(@Body authRequest: AuthRequest): Response<UserResponse>
}