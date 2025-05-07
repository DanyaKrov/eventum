package com.example.eventum.screen_wishList.data.remote.service

import android.util.Log
import com.example.eventum.data.remote.model.request.WishListRemoteRequest
import com.example.eventum.data.remote.model.response.WishListRemoteResponse
import com.example.eventum.screen_wishList.data.remote.dataSource.WishListRemoteDataSource
import com.example.eventum.screen_wishList.data.remote.repository.WishListRemoteRepository
import javax.inject.Inject

class WishListRemoteService @Inject constructor(
    private val dataSource: WishListRemoteDataSource
): WishListRemoteRepository {
    override suspend fun getWishList(userId: Long): WishListRemoteResponse =
        dataSource.getUserWishList(userId)

    override suspend fun changeVisibility(userId: Long, wishListRemoteRequest: WishListRemoteRequest) {
        dataSource.update(userId, wishListRemoteRequest)
    }
}