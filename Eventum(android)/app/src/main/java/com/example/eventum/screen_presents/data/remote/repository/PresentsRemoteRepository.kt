package com.example.eventum.screen_presents.data.remote.repository

import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest
import com.example.eventum.screen_presents.domain.model.Present
import retrofit2.http.Body

interface PresentsRemoteRepository {
    suspend fun getAll(wishListId: Long): List<Present>
    suspend fun delete(presentId: Long): String
    suspend fun insert(present: Present): Present
    suspend fun update(id: Long, present: Present): String
}