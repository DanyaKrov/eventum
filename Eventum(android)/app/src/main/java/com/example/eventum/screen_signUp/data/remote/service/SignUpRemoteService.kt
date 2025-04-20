package com.example.eventum.screen_signUp.data.remote.service

import com.example.eventum.data.remote.model.request.UserRemoteRequest
import com.example.eventum.data.remote.model.response.UserRemote
import com.example.eventum.screen_signUp.data.remote.dataSource.SignUpRemoteDataSource
import com.example.eventum.screen_signUp.data.remote.repository.SignUpRemoteRepository
import javax.inject.Inject

class SignUpRemoteService @Inject constructor(
    private val dataSource: SignUpRemoteDataSource
): SignUpRemoteRepository {
    override suspend fun createUser(userRemoteRequest: UserRemoteRequest): UserRemote = dataSource.createUser(userRemoteRequest)
}