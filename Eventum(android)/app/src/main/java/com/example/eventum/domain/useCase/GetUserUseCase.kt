package com.example.eventum.domain.useCase

import com.example.eventum.common.Resource
import com.example.eventum.data.roomDatabase.entity.UserEntity
import com.example.eventum.data.roomDatabase.repository.UserLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserLocalRepository
) { // useCase to get active user. But maybe in the future local database won't contain singular user
    operator fun invoke(): Flow<Resource<UserEntity>> =
        flow{
            try {
                emit(Resource.Loading())
                val user = repository.getUser()
                emit(Resource.Success(user))
            }
            catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server"))
            }
            catch (e: Exception) {
                emit(Resource.Error("Unexpected error occurred"))
            }
    }
}