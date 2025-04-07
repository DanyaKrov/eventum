package com.example.eventum.screen_settings.domain.useCase

import com.example.eventum.screen_settings.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateWishListVisibilityUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(userId: Long, isOpen: Boolean): Boolean =
        repository.updateWishListVisibility(userId, isOpen)
}