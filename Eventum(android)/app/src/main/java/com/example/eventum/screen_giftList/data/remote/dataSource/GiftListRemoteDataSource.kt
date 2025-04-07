package com.example.eventum.screen_giftList.data.remote.dataSource

import com.example.eventum.data.remote.model.GiftListRemote
import com.example.eventum.data.remote.model.GiftRemote
import com.example.eventum.screen_contacts.domain.model.Contact
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GiftListRemoteDataSource {
    @GET("giftLists/{id}")
    suspend fun findById(@Path("id") id: Long): GiftListRemote

    @PUT("giftLists/{id}")
    suspend fun updateById(@Path("id") id: Long, @Body giftListRemote: GiftListRemote): GiftListRemote

    @POST("giftLists")
    suspend fun create(@Body giftListRemote: GiftListRemote): GiftListRemote


    @DELETE("giftLists/{id}")
    suspend fun deleteById(@Path("id") id: Long): String

    // method to add new gift?
}