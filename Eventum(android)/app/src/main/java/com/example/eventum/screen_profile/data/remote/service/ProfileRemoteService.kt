package com.example.eventum.screen_profile.data.remote.service

import com.example.eventum.data.remote.model.response.UserRemote
import com.example.eventum.screen_profile.data.remote.dataSource.ProfileRemoteDataSource
import com.example.eventum.screen_profile.data.remote.repository.ProfileRemoteRepository
import retrofit2.Response
import javax.inject.Inject

class ProfileRemoteService @Inject constructor(
    private val dataSource: ProfileRemoteDataSource
): ProfileRemoteRepository {
    override suspend fun updateUser(user: UserRemote): Response<String> =
        dataSource.updateUser(user)
}