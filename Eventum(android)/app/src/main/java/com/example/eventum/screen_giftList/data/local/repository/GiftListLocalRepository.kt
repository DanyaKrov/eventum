package com.example.eventum.screen_giftList.data.local.repository

import com.example.eventum.data.local.entity.GiftEntity
import com.example.eventum.data.local.entity.GiftListEntity
import com.example.eventum.data.local.entity.GiftListWithGifts
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.model.GiftList

interface GiftListLocalRepository {
    suspend fun getGiftListWithGifts(remoteId: Long): GiftListWithGifts
    suspend fun deleteGiftList(remoteId: Long): Boolean
    suspend fun deleteGift(remoteId: Long): Boolean
    suspend fun updateGift(gift: GiftEntity): Boolean
    suspend fun updateGiftList(giftList: GiftListEntity): Boolean
    suspend fun createGift(gift: GiftEntity): Boolean
    suspend fun createGiftList(giftList: GiftListEntity): Boolean
}