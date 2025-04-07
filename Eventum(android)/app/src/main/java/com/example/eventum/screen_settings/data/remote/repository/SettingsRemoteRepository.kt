package com.example.eventum.screen_settings.data.remote.repository


interface SettingsRemoteRepository {
    suspend fun updateUserWishListVisibility(userId: Long, isOpen: Boolean)
}