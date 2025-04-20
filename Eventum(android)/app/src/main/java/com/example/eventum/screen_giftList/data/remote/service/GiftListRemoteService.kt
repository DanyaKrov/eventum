package com.example.eventum.screen_giftList.data.remote.service

import com.example.eventum.data.remote.model.request.CustomGiftRemoteRequest
import com.example.eventum.data.remote.model.response.GiftRemoteResponse
import com.example.eventum.screen_giftList.data.remote.dataSource.GiftRemoteDataSource
import com.example.eventum.screen_giftList.data.remote.repository.GiftListRemoteRepository
import javax.inject.Inject

class GiftListRemoteService @Inject constructor(
    private val giftDataSource: GiftRemoteDataSource,
): GiftListRemoteRepository {
    override suspend fun getGifts(contactId: Long): List<GiftRemoteResponse> =
        giftDataSource.getGifts(contactId)

    override suspend fun deleteGift(id: Long): Boolean {
        return try {
            giftDataSource.deleteById(id)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun updateCustomGift(giftRemoteId: Long, gift: CustomGiftRemoteRequest): Boolean {
        return try {
            giftDataSource.updateCustom(giftRemoteId, gift)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun createCustomGift(contactRemoteId: Long, gift: CustomGiftRemoteRequest): GiftRemoteResponse =
        giftDataSource.createCustomGift(contactRemoteId, gift)
}