package com.example.eventum.screen_signUp.data.remote.dataSource

import com.example.eventum.data.remote.model.response.UserRemoteRequest
import com.example.eventum.data.remote.model.response.UserRemote
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface UsersRemoteDataSource {
    @POST("users/{email}")
    suspend fun checkEmail(@Path("email") email: String): Boolean
    @POST("users")
    suspend fun createUser(@Body user: UserRemoteRequest): UserRemote
}