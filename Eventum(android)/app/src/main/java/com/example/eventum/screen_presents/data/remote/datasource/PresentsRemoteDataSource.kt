package com.example.eventum.screen_presents.data.remote.dataSource

import com.example.eventum.data.remote.model.request.PresentRemoteRequest
import com.example.eventum.data.remote.model.response.PresentRemoteResponse
import com.example.eventum.screen_presents.domain.model.Present
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PresentsRemoteDataSource {
    @PUT("presents/{id}")
    suspend fun updateById(@Path("id") id: Long, @Body present: PresentRemoteRequest): PresentRemoteResponse

    @POST("users/{userId}/presents")
    suspend fun create(@Path("userId") userId: Long, @Body present: PresentRemoteRequest):
            PresentRemoteResponse

    @DELETE("presents/{id}")
    suspend fun deleteById(@Path("id") id: Long): String

    @GET("wishList/{wishListId}/presents") // later on fix it
    suspend fun getById(@Path("id") presentRemoteId: Long): PresentRemoteResponse
}