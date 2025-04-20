package com.example.eventum.screen_giftList.data.remote.repository

import com.example.eventum.data.remote.model.request.CustomGiftRemoteRequest
import com.example.eventum.data.remote.model.response.GiftRemoteResponse

interface GiftListRemoteRepository {
    suspend fun getGifts(contactId: Long): List<GiftRemoteResponse>
    suspend fun deleteGift(id: Long): Boolean
    suspend fun updateCustomGift(giftRemoteId: Long, gift: CustomGiftRemoteRequest): Boolean
    suspend fun createCustomGift(contactRemoteId: Long, gift: CustomGiftRemoteRequest): GiftRemoteResponse // return same object, but with id
}