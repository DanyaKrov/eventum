package com.example.eventum.screen_giftList.domain.repository

import com.example.eventum.screen_giftList.domain.model.Gift

interface GiftListRepository {
    suspend fun getGifts(contactId: Long, forceRefresh: Boolean): List<Gift>
    suspend fun deleteGift(remoteId: Long): Boolean
    suspend fun updateGift(gift: Gift, giftListId: Long): Boolean
    suspend fun createGift(gift: Gift, giftListId: Long)
}