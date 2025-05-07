package com.example.eventum.screen_giftList.data.local.repository

import com.example.eventum.data.local.model.entity.GiftEntity

interface GiftListLocalRepository {
    suspend fun getGifts(contactRemoteId: Long): List<GiftEntity>
    suspend fun deleteGift(remoteId: Long): Boolean
    suspend fun deleteAll()
    suspend fun updateGift(gift: GiftEntity): Boolean
    suspend fun createCustomGift(gift: GiftEntity): Boolean
}