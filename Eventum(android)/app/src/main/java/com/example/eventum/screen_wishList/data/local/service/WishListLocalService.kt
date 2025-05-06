package com.example.eventum.screen_wishList.data.local.service

import android.util.Log
import com.example.eventum.data.local.dao.PresentDao
import com.example.eventum.data.local.dao.WishListDao
import com.example.eventum.data.local.model.entity.PresentEntity
import com.example.eventum.data.local.model.entity.WishListEntity
import com.example.eventum.data.local.model.entity.WishListWithPresents
import com.example.eventum.screen_wishList.data.local.repository.WishListLocalRepository
import javax.inject.Inject

class WishListLocalService @Inject constructor(
    private val dao: WishListDao,
    private val presentDao: PresentDao
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
            presents.onEach {
                presentDao.insert(it)
            }
            true
        }
        catch (e: Exception) {
            false
        }
    }
}