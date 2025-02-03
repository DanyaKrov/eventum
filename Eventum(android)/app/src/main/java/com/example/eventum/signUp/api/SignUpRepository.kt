package com.example.eventum.signUp.api

import com.example.eventum.api.model.UserRequest
import com.example.eventum.api.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUpRepository {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
    @GET("users")
    suspend fun getUser(@Query("userId") id: Long): List<UserResponse>
    @POST("users")
    suspend fun createUser(@Body user: UserRequest): UserResponse
}