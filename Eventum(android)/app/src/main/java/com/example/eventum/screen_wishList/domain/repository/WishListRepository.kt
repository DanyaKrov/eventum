package com.example.eventum.screen_wishList.domain.repository

import com.example.eventum.screen_wishList.domain.model.WishList

interface WishListRepository {
    suspend fun getWishList(userRemoteId: Long, forceRefresh: Boolean = false): WishList
    suspend fun changeVisibility(visibilityCode: String): Boolean
}