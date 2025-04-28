package com.example.eventum.screen_giftList.domain.repository

import com.example.eventum.screen_giftList.domain.model.Gift

interface GiftListRepository {
    suspend fun getGifts(contactId: Long, forceRefresh: Boolean): List<Gift>
    suspend fun deleteGift(remoteId: Long): Boolean
    suspend fun updateCustomGift(gift: Gift): Boolean
    suspend fun createCustomGift(gift: Gift, contactRemoteId: Long)
}