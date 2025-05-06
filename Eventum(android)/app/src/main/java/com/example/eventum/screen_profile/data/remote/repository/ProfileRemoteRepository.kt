package com.example.eventum.screen_profile.data.remote.repository

import com.example.eventum.data.remote.model.request.update.UserRemoteUpdateRequest
import com.example.eventum.data.remote.model.response.UserRemote
import retrofit2.Response

interface ProfileRemoteRepository {
    suspend fun updateUser(userId: Long, user: UserRemoteUpdateRequest): Response<String> // maybe request model will change
    // and maybe response model will change too
}