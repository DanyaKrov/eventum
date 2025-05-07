package com.example.eventum.screen_giftList.data.local.service

import com.example.eventum.data.local.model.entity.GiftEntity
import com.example.eventum.screen_giftList.data.local.dao.GiftDao
import com.example.eventum.screen_giftList.data.local.repository.GiftListLocalRepository
import javax.inject.Inject

class GiftListLocalService @Inject constructor(
    private val giftDao: GiftDao
): GiftListLocalRepository {

    override suspend fun getGifts(contactRemoteId: Long): List<GiftEntity> =
        giftDao.getGiftsByContactRemoteId(contactRemoteId)

    override suspend fun deleteGift(remoteId: Long): Boolean {
        return try {
            giftDao.delete(remoteId)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteAll() = giftDao.deleteAll()

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

    override suspend fun createCustomGift(gift: GiftEntity): Boolean {
        return try {
            giftDao.insert(gift)
            true
        }
        catch (e: Exception) {
            false
        }
    }
}