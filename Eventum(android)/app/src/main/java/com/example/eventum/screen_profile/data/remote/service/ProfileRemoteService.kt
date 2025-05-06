package com.example.eventum.screen_profile.data.remote.service

import com.example.eventum.data.remote.model.request.update.UserRemoteUpdateRequest
import com.example.eventum.data.remote.model.response.UserRemote
import com.example.eventum.screen_profile.data.remote.dataSource.ProfileRemoteDataSource
import com.example.eventum.screen_profile.data.remote.repository.ProfileRemoteRepository
import retrofit2.Response
import javax.inject.Inject

class ProfileRemoteService @Inject constructor(
    private val dataSource: ProfileRemoteDataSource
): ProfileRemoteRepository {
    override suspend fun updateUser(userId: Long, user: UserRemoteUpdateRequest): Response<String> =
        dataSource.updateUser(userId, user)
}