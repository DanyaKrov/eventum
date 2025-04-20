package com.example.eventum.screen_wishList.data.remote.dataSource

import com.example.eventum.data.remote.model.request.WishListRemoteRequest
import com.example.eventum.data.remote.model.response.WishListRemoteResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface WishListRemoteDataSource {
    @GET("users/{userId}/wishlist")
    suspend fun getUserWishList(@Path("userId") userId: Long): WishListRemoteResponse

    @PUT("users/{userId}/wishlist")
    suspend fun update(@Path("userId") userId: Long, @Body request: WishListRemoteRequest): WishListRemoteResponse
}