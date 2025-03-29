package com.example.eventum.screen_login.data.service

import com.example.eventum.data.remote.model.UserResponse
import com.example.eventum.screen_login.data.remote.repository.LoginRemoteRepository
import com.example.eventum.screen_login.domain.model.AuthRequest
import com.example.eventum.screen_login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginService @Inject constructor(
    private val remoteRepository: LoginRemoteRepository
): LoginRepository {
    override suspend fun authorise(authRequest: AuthRequest): Result<UserResponse?> {
        return try {
            val response = remoteRepository.authorise(authRequest)
            if (response.isSuccessful) {
                Result.success(response.body())
            }
            else
                Result.failure(Exception("Email or password is incorrect"))
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }
}