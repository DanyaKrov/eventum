package com.example.eventum.screen_profile.data.remote.dataSource

import com.example.eventum.data.remote.model.UserResponse
import com.example.eventum.screen_presents.domain.model.Present
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileRemoteDataSource {
    @PUT("users") // this will change soon
    suspend fun updateUser(@Body user: UserResponse): Response<String>
}