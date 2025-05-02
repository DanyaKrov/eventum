package com.example.eventum.screen_login.domain.useCase

import android.util.Log
import com.example.eventum.data.local.preferences.UserPreferences
import com.example.eventum.data.remote.model.response.UserRemote
import com.example.eventum.data.local.repository.UserLocalRepository
import com.example.eventum.screen_login.domain.model.AuthRequest
import com.example.eventum.screen_login.domain.repository.LoginRepository
import com.example.eventum.util.mapper.UserMapper
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val roomUserLocalRepository: UserLocalRepository,
    private val loginRepository: LoginRepository,
    private val userMapper: UserMapper,
    private val userPreferences: UserPreferences
) {
    suspend fun execute(authRequest: AuthRequest) : Result<UserRemote?>{
        try {
            val result = loginRepository.authorise(authRequest)
            if (result.isFailure)
                return result
            result.getOrThrow()?.let {
                Log.i("testing", it.toString())
                userPreferences.saveUserId(it.id)
                // if user authorised, he needs to be saved to local database
                roomUserLocalRepository.insertUser(userMapper.createEntity(it))
            }
            return result

        }
        catch (e: Exception) {
           return Result.failure(e)
        }
    }
}