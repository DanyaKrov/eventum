package com.example.eventum.screen_giftList.data.remote.repository

import com.example.eventum.data.remote.model.response.GiftRemote

interface GiftListRemoteRepository {
    suspend fun getGifts(contactId: Long): List<GiftRemote>
    suspend fun deleteGift(id: Long): Boolean
    suspend fun updateGift(gift: GiftRemote): Boolean
    suspend fun createGift(gift: GiftRemote): GiftRemote // return same object, but with id
}