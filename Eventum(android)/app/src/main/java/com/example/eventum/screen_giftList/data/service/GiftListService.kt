package com.example.eventum.screen_giftList.data.service

import com.example.eventum.screen_giftList.data.local.repository.GiftListLocalRepository
import com.example.eventum.screen_giftList.data.remote.repository.GiftListRemoteRepository
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.repository.GiftListRepository
import com.example.eventum.screen_presents.data.service.PresentsService
import com.example.eventum.screen_presents.domain.repository.PresentsRepository
import com.example.eventum.util.mapper.GiftMapper
import javax.inject.Inject

class GiftListService @Inject constructor(
    private val localRepository: GiftListLocalRepository,
    private val remoteRepository: GiftListRemoteRepository,
    private val presentsRepository: PresentsRepository,
    private val giftMapper: GiftMapper

    ): GiftListRepository {
    override suspend fun getGifts(contactId: Long, forceRefresh: Boolean): List<Gift> {
        return if(forceRefresh) {
            try {
                updateLocalEntity(contactId)
            }
            catch (e: Exception) {
                getModelFromLocalEntity(contactId)
            }
        }
        else {
            try {
                getModelFromLocalEntity(contactId)
            }
            catch (e: Exception) {
                updateLocalEntity(contactId)
            }
        }
    }

    private suspend fun getModelFromLocalEntity(contactRemoteId: Long): List<Gift> {
        val localGifts = localRepository.getGifts(contactRemoteId)
        return localGifts.map {
            val present = presentsRepository.getPresent(it.presentRemoteId)
            giftMapper.fromEntityToModel(present = present,
                giftEntity = it) // add gift state later on
        }
    }

    private suspend fun updateLocalEntity(contactId: Long): List<Gift> {
        val remoteGifts = remoteRepository.getGifts(contactId)
        remoteGifts.forEach {
            localRepository.updateGift(giftMapper.fromRemoteToEntity(it))
        }
        return remoteGifts.map {giftMapper.fromRemoteToModel(it)}
    }

    override suspend fun deleteGift(remoteId: Long): Boolean {
        return try {
            localRepository.deleteGift(remoteId) &&
                    remoteRepository.deleteGift(remoteId)
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun updateGift(gift: Gift, giftListId: Long): Boolean {
        return try {
            localRepository.updateGift(giftMapper.fromModelToEntity(gift,
                giftListId)) &&
                    remoteRepository.updateCustomGift(gift.remoteId,
                        giftMapper.fromModelToCustomRequest(gift))
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun createGift(gift: Gift, giftListId: Long) {
        try {
            localRepository.createGift(giftMapper.fromModelToEntity(gift, giftListId))
            remoteRepository.createCustomGift(gift.contactId, giftMapper.fromModelToCustomRequest(gift))
        }
        catch (_: Exception) {
        }
    }
}