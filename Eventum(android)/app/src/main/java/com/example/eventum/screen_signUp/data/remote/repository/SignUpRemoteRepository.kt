package com.example.eventum.screen_signUp.data.remote.repository

import com.example.eventum.data.remote.model.UserRemoteRequest
import com.example.eventum.data.remote.model.UserRemote

interface SignUpRemoteRepository {
    suspend fun createUser(userRemoteRequest: UserRemoteRequest): UserRemote
    suspend fun checkAvailability(email: String): Boolean // check if email is used already in the system
}