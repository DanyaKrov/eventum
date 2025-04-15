package com.example.eventum.screen_wishList.data.remote.service

import com.example.eventum.data.remote.model.response.WishListRemote
import com.example.eventum.screen_wishList.data.remote.dataSource.WishListRemoteDataSource
import com.example.eventum.screen_wishList.data.remote.repository.WishListRemoteRepository
import javax.inject.Inject

class WishListRemoteService @Inject constructor(
    private val dataSource: WishListRemoteDataSource
): WishListRemoteRepository {
    override suspend fun getWishList(id: Long): WishListRemote =
        dataSource.getById(id)

    override suspend fun updateWishList(newWishList: WishListRemote): Boolean {
        return try {
            dataSource.update(newWishList)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteWishList(id: Long): Boolean {
        return try {
            dataSource.deleteById(id)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun createWishList(wishList: WishListRemote): Long {
        val savedWishList = dataSource.create(wishList)
        return savedWishList.id
    }
}