package com.example.eventum.screen_giftList.domain.repository

import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.model.GiftList

interface GiftListRepository {
    suspend fun getGiftList(refresh: Boolean, remoteId: Long): GiftList
    suspend fun deleteGiftList(remoteId: Long): Boolean
    suspend fun deleteGift(remoteId: Long): Boolean
    suspend fun updateGift(gift: Gift, giftListId: Long): Boolean
    suspend fun createGift(gift: Gift, giftListId: Long)
}