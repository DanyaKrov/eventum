package com.example.eventum.screen_giftList.data.local.service

import com.example.eventum.data.local.entity.GiftEntity
import com.example.eventum.data.local.entity.GiftListEntity
import com.example.eventum.data.local.entity.GiftListWithGifts
import com.example.eventum.screen_giftList.data.local.dao.GiftDao
import com.example.eventum.screen_giftList.data.local.dao.GiftListDao
import com.example.eventum.screen_giftList.data.local.repository.GiftListLocalRepository
import javax.inject.Inject

class GiftListLocalService @Inject constructor(
    private val giftDao: GiftDao,
    private val giftListDao: GiftListDao
): GiftListLocalRepository {
    override suspend fun getGiftListWithGifts(remoteId: Long): GiftListWithGifts =
        giftListDao.getGiftListWithGifts(remoteId)

    override suspend fun deleteGiftList(remoteId: Long): Boolean {
        return try {
            giftListDao.delete(remoteId)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteGift(remoteId: Long): Boolean {
        return try {
            giftDao.delete(remoteId)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun updateGift(gift: GiftEntity): Boolean {
        return try {
            giftDao.update(gift)
            // maybe need to update giftList too
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun updateGiftList(giftList: GiftListEntity): Boolean {
        return try {
            giftListDao.update(giftList)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun createGift(gift: GiftEntity): Boolean {
        return try {
            giftDao.insert(gift)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun createGiftList(giftList: GiftListEntity): Boolean {
        return try {
            giftListDao.insert(giftList)
            true
        }
        catch (e: Exception) {
            false
        }
    }
}