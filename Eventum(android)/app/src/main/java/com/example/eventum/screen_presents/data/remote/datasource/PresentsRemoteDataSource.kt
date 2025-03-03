package com.example.eventum.screen_presents.data.remote.datasource

import com.example.eventum.screen_presents.domain.model.Present
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PresentsRemoteDataSource {
    @PUT("presents/{id}")
    suspend fun updateById(@Path("id") id: Long, @Body present: Present): Present

    @POST("presents")
    suspend fun create(@Body present: Present): Present

    @DELETE("presents/{id}")
    suspend fun deleteById(@Path("id") id: Long): String
}