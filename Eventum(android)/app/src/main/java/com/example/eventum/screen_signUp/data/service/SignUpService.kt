package com.example.eventum.screen_signUp.data.service

import com.example.eventum.data.remote.model.UserRemoteRequest
import com.example.eventum.data.remote.model.UserRemote
import com.example.eventum.screen_signUp.data.remote.repository.SignUpRemoteRepository
import com.example.eventum.screen_signUp.domain.model.SignUpRequest
import com.example.eventum.screen_signUp.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpService @Inject constructor(
    private val remoteRepository: SignUpRemoteRepository): SignUpRepository {
    override suspend fun signUp(signUpRequest: SignUpRequest): UserRemote =
        remoteRepository.createUser(UserRemoteRequest(signUpRequest.name, signUpRequest.email, signUpRequest.password))
}