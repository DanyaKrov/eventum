package com.example.eventum.screen_profile.data.remote.repository

import com.example.eventum.data.remote.model.UserRemote
import retrofit2.Response

interface ProfileRemoteRepository {
    suspend fun updateUser(user: UserRemote): Response<String> // maybe request model will change
    // and maybe response model will change too
}