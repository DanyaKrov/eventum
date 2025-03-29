package com.example.eventum.screen_wishList.data.remote.service

import com.example.eventum.data.remote.model.WishListResponse
import com.example.eventum.screen_wishList.data.remote.dataSource.WishListRemoteDataSource
import com.example.eventum.screen_wishList.data.remote.repository.WishListRemoteRepository
import javax.inject.Inject

class WishListRemoteService @Inject constructor(
    private val dataSource: WishListRemoteDataSource
): WishListRemoteRepository {
    override suspend fun getWishList(id: Long): WishListResponse =
        dataSource.getById(id)

    override suspend fun updateWishList(newWishList: WishListResponse): Boolean {
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

    override suspend fun createWishList(wishList: WishListResponse): Long {
        val savedWishList = dataSource.create(wishList)
        return savedWishList.id
    }
}