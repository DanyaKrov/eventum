package com.example.eventum.screen_wishList.data.local.service

import com.example.eventum.data.local.dao.WishListDao
import com.example.eventum.data.local.model.entity.PresentEntity
import com.example.eventum.data.local.model.entity.WishListEntity
import com.example.eventum.data.local.model.entity.WishListWithPresents
import com.example.eventum.screen_wishList.data.local.repository.WishListLocalRepository
import javax.inject.Inject

class WishListLocalService @Inject constructor(
    private val dao: WishListDao
): WishListLocalRepository {
    override suspend fun getWishList(wishListRemoteId: Long): WishListWithPresents =
        dao.getWishListWithPresents(wishListRemoteId)

    override suspend fun updateWishList(
        wishList: WishListEntity,
        presents: List<PresentEntity>
    ): Boolean {
        return try {
            dao.updateWishListWithPresents(wishList, presents)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteWishList(wishListRemoteId: Long): Boolean {
        return try {
            dao.delete(wishListRemoteId)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun createWishList(
        wishList: WishListEntity,
        presents: List<PresentEntity>
    ): Boolean {
        return try {
            dao.insertWishList(wishList)
            dao.insertPresents(presents)
            true
        }
        catch (e: Exception) {
            false
        }
    }
}