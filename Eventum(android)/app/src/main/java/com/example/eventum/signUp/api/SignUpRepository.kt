package com.example.eventum.signUp.api

import com.example.eventum.signUp.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SignUpRepository {
    @GET("users")
    suspend fun getUsers(): List<User>
}