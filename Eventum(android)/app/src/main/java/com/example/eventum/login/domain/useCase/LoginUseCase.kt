package com.example.eventum.login.domain.useCase

import com.example.eventum.data.api.model.UserResponse
import com.example.eventum.data.roomDatabase.mapper.UserMapper
import com.example.eventum.data.roomDatabase.repository.UserLocalRepository
import com.example.eventum.login.domain.model.AuthRequest
import com.example.eventum.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val roomUserLocalRepository: UserLocalRepository,
    private val loginRepository: LoginRepository,
    private val userMapper: UserMapper,
) {
    suspend fun execute(authRequest: AuthRequest) : Result<UserResponse?>{
        try {
            val result = loginRepository.authorise(authRequest)
            if (result.isFailure)
                return result
            result.getOrThrow()?.let {  // if user authorised, he needs to be saved to local database
                roomUserLocalRepository.insertUser(userMapper.createUser(it))
            }
            return result

        }
        catch (e: Exception) {
           return Result.failure(e)
        }
    }
}