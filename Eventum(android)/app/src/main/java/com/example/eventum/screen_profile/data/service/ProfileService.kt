package com.example.eventum.screen_profile.data.service

import com.example.eventum.data.local.repository.UserLocalRepository
import com.example.eventum.domain.model.User
import com.example.eventum.screen_profile.data.local.repository.ProfileLocalRepository
import com.example.eventum.screen_profile.data.remote.repository.ProfileRemoteRepository
import com.example.eventum.screen_profile.domain.repository.ProfileRepository
import com.example.eventum.util.mapper.UserMapper
import javax.inject.Inject

class ProfileService @Inject constructor(
    private val remoteRepository: ProfileRemoteRepository,
    private val localRepository: ProfileLocalRepository,
    private val mapper: UserMapper
): ProfileRepository {
    override suspend fun updateUser(user: User): Boolean {
        return if (localRepository.updateUser(mapper.fromModelToEntity(user))) {
            val response = remoteRepository.updateUser(mapper.fromModelToRemoteEntity(user))
            response.isSuccessful // need to handle errors better
        }
        else
            false // this option is need to be handled better
    }
}