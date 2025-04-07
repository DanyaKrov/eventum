package com.example.eventum.screen_settings.domain.repository

import com.example.eventum.data.local.preferences.model.WishListVisibility

interface SettingsRepository {
    suspend fun updateWishListVisibility(userId: Long, isOpen: Boolean): Boolean
}