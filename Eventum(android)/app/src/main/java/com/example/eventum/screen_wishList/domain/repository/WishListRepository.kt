package com.example.eventum.screen_wishList.domain.repository

import com.example.eventum.screen_wishList.domain.model.WishList

interface WishListRepository {
    suspend fun getWishList(wishListId: Long, forceRefresh: Boolean = false): WishList
    suspend fun updateWishList(wishList: WishList): Boolean
    suspend fun changeVisibility(visibilityCode: String): Boolean
    suspend fun deleteWishList(wishListRemoteId: Long): Boolean
    suspend fun createWishList(wishList: WishList): Boolean
}