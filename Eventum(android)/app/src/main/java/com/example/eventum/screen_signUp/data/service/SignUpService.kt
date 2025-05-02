package com.example.eventum.screen_signUp.data.service

import com.example.eventum.data.remote.model.request.UserRemoteRequest
import com.example.eventum.data.remote.model.response.UserRemote
import com.example.eventum.domain.model.User
import com.example.eventum.screen_signUp.data.remote.repository.SignUpRemoteRepository
import com.example.eventum.screen_signUp.domain.model.SignUpRequest
import com.example.eventum.screen_signUp.domain.repository.SignUpRepository
import com.example.eventum.screen_users.data.local.repository.UsersLocalRepository
import com.example.eventum.util.mapper.UserMapper
import javax.inject.Inject

class SignUpService @Inject constructor(
    private val remoteRepository: SignUpRemoteRepository,
    private val localRepository: UsersLocalRepository,
    private val mapper: UserMapper
    ): SignUpRepository {
    override suspend fun signUp(signUpRequest: SignUpRequest): User {
        val createdRemoteUser = remoteRepository.createUser(
            UserRemoteRequest(name = signUpRequest.name,
                email = signUpRequest.email,
                password = signUpRequest.password)
        )
        localRepository.saveUser(mapper.fromRemoteToEntity(createdRemoteUser))
        return mapper.fromRemoteToModel(createdRemoteUser)
    }
}