package com.example.eventum.screen_signUp.data.remote.repository

import com.example.eventum.data.remote.model.request.UserRemoteRequest
import com.example.eventum.data.remote.model.response.UserRemote

interface SignUpRemoteRepository {
    suspend fun createUser(userRemoteRequest: UserRemoteRequest): UserRemote
}