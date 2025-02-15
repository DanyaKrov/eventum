package com.example.eventum.mainPage.api

import com.example.eventum.mainPage.api.entity.EventResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EventsRepository {
    @GET("events")
    suspend fun getEvents(@Query("userId") userId: Long): List<EventResponse>
}