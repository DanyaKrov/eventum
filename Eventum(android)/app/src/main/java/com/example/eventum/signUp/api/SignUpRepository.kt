package com.example.eventum.signUp.api

import com.example.eventum.signUp.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpRepository {
    @POST("users")
    suspend fun createUser(@Body user: User): User
}