package com.example.eventum.screen_presents.domain.repository

import com.example.eventum.screen_presents.domain.model.Present

interface PresentsRepository {
    suspend fun getPresents(wishListId: Long, forceRefresh: Boolean): List<Present>
    suspend fun deletePresent(present: Present): String
    suspend fun editPresent(present: Present): String
    suspend fun createPresent(present: Present)
}