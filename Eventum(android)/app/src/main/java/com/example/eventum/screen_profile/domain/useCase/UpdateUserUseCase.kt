package com.example.eventum.screen_profile.domain.useCase

import com.example.eventum.domain.model.User
import com.example.eventum.screen_profile.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend fun invoke(user: User): Boolean = profileRepository.updateUser(user)
}