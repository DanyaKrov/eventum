package com.example.eventum.screen_settings.data.remote.service

import com.example.eventum.screen_settings.data.remote.dataSource.SettingsRemoteDataSource
import com.example.eventum.screen_settings.data.remote.repository.SettingsRemoteRepository
import javax.inject.Inject

class SettingsRemoteService @Inject constructor(
    private val dataSource: SettingsRemoteDataSource
): SettingsRemoteRepository {
    override suspend fun updateUserWishListVisibility(userId: Long, isOpen: Boolean) {
        dataSource.updateUserWishListVisibility(userId, isOpen)
    }
}