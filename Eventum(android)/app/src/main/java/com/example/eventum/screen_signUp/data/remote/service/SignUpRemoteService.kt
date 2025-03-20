package com.example.eventum.screen_signUp.data.remote.service

import com.example.eventum.data.remote.model.UserRequest
import com.example.eventum.data.remote.model.UserResponse
import com.example.eventum.screen_signUp.data.remote.dataSource.UsersRemoteDataSource
import com.example.eventum.screen_signUp.data.remote.repository.SignUpRemoteRepository
import com.example.eventum.screen_signUp.domain.model.SignUpRequest
import javax.inject.Inject

class SignUpRemoteService @Inject constructor(
    private val dataSource: UsersRemoteDataSource
): SignUpRemoteRepository {
    override suspend fun createUser(userRequest: UserRequest): UserResponse = dataSource.createUser(userRequest)

    override suspend fun checkAvailability(email: String): Boolean = dataSource.checkEmail(email)
}