package com.example.eventum.screen_mainPage.data.remote.dataSource

import com.example.eventum.data.remote.model.response.EventRemote
import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest
import retrofit2.http.DELETE
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EventsRemoteDataSource {
    @GET("events/{id}")
    suspend fun getById(@Path("id") id: Long): EventRemote

    @PUT("events/{id}")
    suspend fun updateById(@Path("id") id: Long, @Body event:EventRequest): EventRemote

    @POST("events")
    suspend fun create(@Body event: EventRequest): EventRemote

    @DELETE("events/{id}")
    suspend fun deleteById(@Path("id") id: Long): String
}