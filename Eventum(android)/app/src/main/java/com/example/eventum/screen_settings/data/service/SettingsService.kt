package com.example.eventum.screen_settings.data.service

import com.example.eventum.screen_settings.data.remote.repository.SettingsRemoteRepository
import com.example.eventum.screen_settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsService @Inject constructor(
    private val remoteRepository: SettingsRemoteRepository
): SettingsRepository {
    override suspend fun updateWishListVisibility(userId: Long, isOpen: Boolean): Boolean {
        return try {
            remoteRepository.updateUserWishListVisibility(userId, isOpen)
            true
        }
        catch (e: Exception) {
            false
        }
    }
}