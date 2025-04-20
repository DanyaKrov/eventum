package com.example.eventum.screen_presents.data.local.repository

import com.example.eventum.data.local.model.entity.PresentEntity
import com.example.eventum.screen_presents.data.local.model.PresentRequest
import com.example.eventum.screen_presents.data.local.model.PresentResponse
import com.example.eventum.screen_presents.domain.model.Present

interface PresentsLocalRepository {
    suspend fun insert(present: PresentEntity)
    suspend fun getPresents(wishListId: Long): List<PresentEntity>
    suspend fun getPresent(presentRemoteId: Long): PresentEntity
    suspend fun updatePresent(newPresent: PresentEntity): String
    suspend fun deletePresent(id: Long): String
    suspend fun deleteAll(wishListId: Long): String
}