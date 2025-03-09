package com.example.eventum.screen_presents.data.remote.dataSource

import com.example.eventum.screen_presents.domain.model.Present
import retrofit2.http.GET
import retrofit2.http.Path

interface WishListRemoteDataSource {
    @GET("wishList/{wishListId}/presents")
    suspend fun getAll(@Path("wishListId") wishListId: Long): List<Present>
}