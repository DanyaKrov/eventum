package com.example.eventum.screen_wishList.data.remote.repository

import com.example.eventum.data.remote.model.WishListResponse

interface WishListRemoteRepository {
    suspend fun getWishList(id: Long): WishListResponse
    suspend fun updateWishList(newWishList: WishListResponse): Boolean
    suspend fun deleteWishList(id: Long): Boolean
    suspend fun createWishList(wishList: WishListResponse): Long // will return remoteId for local database
}