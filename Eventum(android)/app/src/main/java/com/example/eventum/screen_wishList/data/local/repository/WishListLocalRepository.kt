package com.example.eventum.screen_wishList.data.local.repository

import com.example.eventum.data.local.model.entity.PresentEntity
import com.example.eventum.data.local.model.entity.WishListEntity
import com.example.eventum.data.local.model.entity.WishListWithPresents

interface WishListLocalRepository {
    suspend fun getWishList(wishListRemoteId: Long): WishListWithPresents
    suspend fun updateWishList(wishList: WishListEntity, presents: List<PresentEntity>): Boolean
    suspend fun deleteWishList(wishListRemoteId: Long): Boolean
    suspend fun createWishList(wishList: WishListEntity, presents: List<PresentEntity>): Boolean
}