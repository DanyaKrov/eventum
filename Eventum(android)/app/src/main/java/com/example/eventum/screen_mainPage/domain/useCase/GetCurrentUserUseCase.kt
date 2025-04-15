package com.example.eventum.screen_mainPage.domain.useCase

import com.example.eventum.domain.model.Resource
import com.example.eventum.data.local.repository.UserLocalRepository
import com.example.eventum.domain.model.User
import com.example.eventum.util.mapper.UserMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: UserLocalRepository,
    private val mapper: UserMapper
) {
    operator fun invoke(remoteId: Long): Flow<Resource<User>> =
        flow{
            try {
                emit(Resource.Loading())
                val user = mapper.fromEntityToModel(repository.getUser(remoteId))
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