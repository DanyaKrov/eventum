package com.example.eventum.screen_presents.data.local.repository

import com.example.eventum.screen_presents.data.local.model.PresentRequest
import com.example.eventum.screen_presents.data.local.model.PresentResponse
import com.example.eventum.screen_presents.domain.model.Present

interface PresentsLocalRepository {
    suspend fun insert(present: Present)
    suspend fun getPresents(wishListId: Long): List<Present>
    suspend fun updatePresent(newPresent: Present): String
    suspend fun deletePresent(id: Long): String
    suspend fun deleteAll(wishListId: Long): String
}