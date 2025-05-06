package com.example.eventum.screen_profile.data.remote.dataSource

import com.example.eventum.data.remote.model.request.UserRemoteRequest
import com.example.eventum.data.remote.model.request.update.UserRemoteUpdateRequest
import com.example.eventum.data.remote.model.response.UserRemote
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileRemoteDataSource {
    @PUT("users/{userId}")
    suspend fun updateUser(@Path("userId") userId: Long,
                           @Body updateRequest: UserRemoteUpdateRequest): Response<String>
}