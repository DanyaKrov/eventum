package com.example.eventum.screen_wishList.data.remote.repository

import com.example.eventum.data.remote.model.request.WishListRemoteRequest
import com.example.eventum.data.remote.model.response.WishListRemoteResponse

interface WishListRemoteRepository {
    suspend fun getWishList(userId: Long): WishListRemoteResponse
    suspend fun changeVisibility(userId: Long, wishListRemoteRequest: WishListRemoteRequest)
}