package com.example.eventum.screen_wishList.data.remote.dataSource

import com.example.eventum.data.remote.model.WishListRemote
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WishListRemoteDataSource {
    @GET("wishLists/{id}")
    suspend fun getById(@Path("id") id: Long): WishListRemote
    @DELETE("wishLists/{id}")
    suspend fun deleteById(@Path("id") id: Long): String
    @PUT("wishLists")
    suspend fun update(@Body wishList: WishListRemote): String
    @POST("wishLists")
    suspend fun create(@Body wishList: WishListRemote): WishListRemote
}