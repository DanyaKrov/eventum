package com.example.eventum.screen_signUp.data.remote.service

import com.example.eventum.data.remote.model.UserRemoteRequest
import com.example.eventum.data.remote.model.UserRemote
import com.example.eventum.screen_signUp.data.remote.dataSource.UsersRemoteDataSource
import com.example.eventum.screen_signUp.data.remote.repository.SignUpRemoteRepository
import javax.inject.Inject

class SignUpRemoteService @Inject constructor(
    private val dataSource: UsersRemoteDataSource
): SignUpRemoteRepository {
    override suspend fun createUser(userRemoteRequest: UserRemoteRequest): UserRemote = dataSource.createUser(userRemoteRequest)

    override suspend fun checkAvailability(email: String): Boolean = dataSource.checkEmail(email)
}