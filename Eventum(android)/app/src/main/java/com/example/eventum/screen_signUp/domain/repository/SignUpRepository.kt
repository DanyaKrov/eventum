package com.example.eventum.screen_signUp.domain.repository

import com.example.eventum.data.remote.model.UserRemote
import com.example.eventum.screen_signUp.domain.model.SignUpRequest

interface SignUpRepository {
    suspend fun signUp(signUpRequest: SignUpRequest): UserRemote
}