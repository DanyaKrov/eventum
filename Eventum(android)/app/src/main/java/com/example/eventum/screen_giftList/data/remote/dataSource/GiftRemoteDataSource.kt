package com.example.eventum.screen_giftList.data.remote.dataSource

import com.example.eventum.data.remote.model.GiftListRemote
import com.example.eventum.data.remote.model.GiftRemote
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GiftRemoteDataSource {
    @PUT("gifts{id}")
    suspend fun updateById(@Path("id") id: Long, @Body gift: GiftRemote): GiftRemote

    @POST("gifts")
    suspend fun create(@Body gift: GiftRemote): GiftRemote


    @DELETE("gifts/{id}")
    suspend fun deleteById(@Path("id") id: Long): String
}