package com.example.eventum.screen_presents.data.remote.service

import com.example.eventum.screen_presents.data.remote.dataSource.PresentsRemoteDataSource
import com.example.eventum.screen_presents.data.remote.dataSource.WishListRemoteDataSource
import com.example.eventum.screen_presents.data.remote.repository.PresentsRemoteRepository
import com.example.eventum.screen_presents.domain.model.Present
import javax.inject.Inject

class PresentsRemoteService @Inject constructor(
    private val dataSource: PresentsRemoteDataSource,
    private val wishListDataSource: WishListRemoteDataSource
): PresentsRemoteRepository {
    override suspend fun getAll(wishListId: Long): List<Present> = wishListDataSource.getAll(wishListId)

    override suspend fun delete(presentId: Long): String = dataSource.deleteById(presentId)
    override suspend fun insert(present: Present): Present = dataSource.create(present)

    override suspend fun update(id: Long, present: Present): String {
        return try {
            dataSource.updateById(id, present)
            "Present updated successfully"
        }
        catch (e: Exception) {
            "Error occurred. Present wasn't updated"
        }
    }
}