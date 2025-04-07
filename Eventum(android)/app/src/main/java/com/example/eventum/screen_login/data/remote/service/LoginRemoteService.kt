package com.example.eventum.screen_login.data.remote.service

import com.example.eventum.data.remote.model.UserRemote
import com.example.eventum.screen_login.data.remote.dataSource.LoginRemoteDataSource
import com.example.eventum.screen_login.data.remote.repository.LoginRemoteRepository
import com.example.eventum.screen_login.domain.model.AuthRequest
import retrofit2.Response
import javax.inject.Inject

class LoginRemoteService @Inject constructor(
    private val dataSource: LoginRemoteDataSource
): LoginRemoteRepository {
    override suspend fun authorise(authRequest: AuthRequest): Response<UserRemote> {
        return dataSource.authorise(authRequest)
    }
}