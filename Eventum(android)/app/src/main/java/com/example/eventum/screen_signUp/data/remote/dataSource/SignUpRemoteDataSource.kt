package com.example.eventum.screen_signUp.data.remote.dataSource

import com.example.eventum.data.remote.model.request.UserRemoteRequest
import com.example.eventum.data.remote.model.response.UserRemote
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpRemoteDataSource {
    @POST("register")
    suspend fun createUser(@Body user: UserRemoteRequest): UserRemote
}