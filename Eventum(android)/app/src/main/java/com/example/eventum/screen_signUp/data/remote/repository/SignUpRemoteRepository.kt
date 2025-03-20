package com.example.eventum.screen_signUp.data.remote.repository

import com.example.eventum.data.remote.model.UserRequest
import com.example.eventum.data.remote.model.UserResponse

interface SignUpRemoteRepository {
    suspend fun createUser(userRequest: UserRequest): UserResponse
    suspend fun checkAvailability(email: String): Boolean // check if email is used already in the system
}