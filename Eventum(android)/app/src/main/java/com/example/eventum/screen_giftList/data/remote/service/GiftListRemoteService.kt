package com.example.eventum.screen_giftList.data.remote.service

import com.example.eventum.data.remote.model.GiftListRemote
import com.example.eventum.data.remote.model.GiftRemote
import com.example.eventum.screen_giftList.data.remote.dataSource.GiftListRemoteDataSource
import com.example.eventum.screen_giftList.data.remote.dataSource.GiftRemoteDataSource
import com.example.eventum.screen_giftList.data.remote.repository.GiftListRemoteRepository
import javax.inject.Inject

class GiftListRemoteService @Inject constructor(
    private val giftDataSource: GiftRemoteDataSource,
    private val giftListDataSource: GiftListRemoteDataSource
): GiftListRemoteRepository {
    override suspend fun getGiftList(id: Long): GiftListRemote = giftListDataSource.findById(id)

    override suspend fun deleteGiftList(id: Long): Boolean {
        return try {
            giftListDataSource.deleteById(id)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteGift(id: Long): Boolean {
        return try {
            giftDataSource.deleteById(id)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun updateGift(gift: GiftRemote): Boolean {
        return try {
            giftDataSource.updateById(gift.id, gift)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun createGift(gift: GiftRemote): GiftRemote =
        giftDataSource.create(gift)
}