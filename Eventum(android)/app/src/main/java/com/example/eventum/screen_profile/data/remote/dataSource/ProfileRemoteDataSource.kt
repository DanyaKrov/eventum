package com.example.eventum.screen_profile.data.remote.dataSource

import com.example.eventum.data.remote.model.response.UserRemote
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface ProfileRemoteDataSource {
    @PUT("users") // this will change soon
    suspend fun updateUser(@Body user: UserRemote): Response<String>
}