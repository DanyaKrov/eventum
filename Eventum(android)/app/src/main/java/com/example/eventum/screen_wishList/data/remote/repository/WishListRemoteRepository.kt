package com.example.eventum.screen_wishList.data.remote.repository

import com.example.eventum.data.remote.model.response.WishListRemote

interface WishListRemoteRepository {
    suspend fun getWishList(id: Long): WishListRemote
    suspend fun updateWishList(newWishList: WishListRemote): Boolean
    suspend fun deleteWishList(id: Long): Boolean
    suspend fun createWishList(wishList: WishListRemote): Long // will return remoteId for local database
}