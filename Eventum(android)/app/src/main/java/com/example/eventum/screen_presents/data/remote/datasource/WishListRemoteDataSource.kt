package com.example.eventum.screen_presents.data.remote.datasource

import com.example.eventum.data.roomDatabase.entity.WishListEntity
import com.example.eventum.screen_presents.domain.model.Present
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WishListRemoteDataSource {
    @GET("wishList/{wishListId}/presents")
    suspend fun getAll(@Path("wishListId") wishListId: Long): List<Present>
}