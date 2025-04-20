package com.example.eventum.screen_presents.data.remote.service

import com.example.eventum.data.remote.model.request.PresentRemoteRequest
import com.example.eventum.data.remote.model.response.PresentRemoteResponse
import com.example.eventum.screen_presents.data.remote.dataSource.PresentsRemoteDataSource
import com.example.eventum.screen_presents.data.remote.repository.PresentsRemoteRepository
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_wishList.data.remote.dataSource.WishListRemoteDataSource
import javax.inject.Inject

class PresentsRemoteService @Inject constructor(
    private val dataSource: PresentsRemoteDataSource,
    private val wishListRemoteDataSource: WishListRemoteDataSource
): PresentsRemoteRepository {
    override suspend fun getAll(userId: Long): List<PresentRemoteResponse> =
        wishListRemoteDataSource.getUserWishList(userId).presents

    override suspend fun get(presentId: Long): PresentRemoteResponse = dataSource.getById(presentId)

    override suspend fun delete(presentId: Long): String = dataSource.deleteById(presentId)

    override suspend fun insert(userId: Long, present: PresentRemoteRequest): PresentRemoteResponse =
        dataSource.create(userId, present)

    override suspend fun update(id: Long, present: PresentRemoteRequest): String {
        return try {
            dataSource.updateById(id, present)
            "Present updated successfully"
        }
        catch (e: Exception) {
            "Error occurred. Present wasn't updated"
        }
    }
}