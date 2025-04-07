package com.example.eventum.screen_giftList.data.remote.repository

import com.example.eventum.data.remote.model.GiftListRemote
import com.example.eventum.data.remote.model.GiftRemote
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.model.GiftList

interface GiftListRemoteRepository {
    suspend fun getGiftList(id: Long): GiftListRemote
    suspend fun deleteGiftList(id: Long): Boolean
    suspend fun deleteGift(id: Long): Boolean
    suspend fun updateGift(gift: GiftRemote): Boolean
    suspend fun createGift(gift: GiftRemote): GiftRemote // return same object, but with id
}