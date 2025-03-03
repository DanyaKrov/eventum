package com.example.eventum.screen_login.data.remote.repository

import com.example.eventum.data.api.model.UserResponse
import com.example.eventum.screen_login.data.remote.api.LoginApiService
import com.example.eventum.screen_login.domain.model.AuthRequest
import com.example.eventum.screen_login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginApiRepository @Inject constructor(
    private val loginApiService: LoginApiService
): LoginRepository {
    override suspend fun authorise(authRequest: AuthRequest): Result<UserResponse?> {
        return try {
            val response = loginApiService.authorise(authRequest)
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