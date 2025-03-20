package com.example.eventum.screen_signUp.data.service

import com.example.eventum.data.remote.model.UserRequest
import com.example.eventum.data.remote.model.UserResponse
import com.example.eventum.screen_signUp.data.remote.repository.SignUpRemoteRepository
import com.example.eventum.screen_signUp.domain.model.SignUpRequest
import com.example.eventum.screen_signUp.domain.repository.SignUpRepository
import com.example.eventum.util.mapper.UserMapper
import javax.inject.Inject

class SignUpService @Inject constructor(
    private val remoteRepository: SignUpRemoteRepository): SignUpRepository {
    override suspend fun signUp(signUpRequest: SignUpRequest): UserResponse =
        remoteRepository.createUser(UserRequest(signUpRequest.name, signUpRequest.email, signUpRequest.password))
}