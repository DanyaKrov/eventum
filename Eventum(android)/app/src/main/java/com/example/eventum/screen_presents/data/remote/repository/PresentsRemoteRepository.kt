package com.example.eventum.screen_presents.data.remote.repository

import com.example.eventum.data.remote.model.request.PresentRemoteRequest
import com.example.eventum.data.remote.model.response.PresentRemoteResponse
import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest
import com.example.eventum.screen_presents.domain.model.Present
import retrofit2.http.Body

interface PresentsRemoteRepository {
    suspend fun getAll(userId: Long): List<PresentRemoteResponse>
    suspend fun get(presentId: Long): PresentRemoteResponse
    suspend fun delete(presentId: Long)
    suspend fun insert(userId: Long, present: PresentRemoteRequest): PresentRemoteResponse
    suspend fun update(id: Long, present: PresentRemoteRequest): String
}