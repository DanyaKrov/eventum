package com.example.eventum.login.api

import com.example.eventum.api.model.UserRequest
import com.example.eventum.api.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginRepository {
    @GET("users/getUserByEmail")
    suspend fun getUser(@Query("userEmail") email: String): UserResponse
}