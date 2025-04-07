package com.example.eventum.screen_giftList.data.service

import com.example.eventum.screen_giftList.data.local.repository.GiftListLocalRepository
import com.example.eventum.screen_giftList.data.remote.repository.GiftListRemoteRepository
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.model.GiftList
import com.example.eventum.screen_giftList.domain.repository.GiftListRepository
import com.example.eventum.util.mapper.GiftListMapper
import com.example.eventum.util.mapper.GiftMapper
import javax.inject.Inject

class GiftListService @Inject constructor(
    private val localRepository: GiftListLocalRepository,
    private val remoteRepository: GiftListRemoteRepository,
    private val listMapper: GiftListMapper,
    private val giftMapper: GiftMapper

    ): GiftListRepository {
    override suspend fun getGiftList(refresh: Boolean, remoteId: Long): GiftList {
        return if(refresh) {
            try {
                updateLocalEntity(remoteId)
            }
            catch (e: Exception) {
                getModelFromLocalEntity(remoteId)
            }
        }
        else {
            try {
                getModelFromLocalEntity(remoteId)
            }
            catch (e: Exception) {
                updateLocalEntity(remoteId)
            }
        }
    }

    private suspend fun getModelFromLocalEntity(remoteId: Long): GiftList {
        val localGiftList = localRepository.getGiftListWithGifts(remoteId)
        return listMapper.fromEntityToModel(localGiftList.giftList,
            localGiftList.gifts.map { giftMapper.fromEntityToModel(it) })
    }

    private suspend fun updateLocalEntity(remoteId: Long): GiftList {
        val remoteGiftList = remoteRepository.getGiftList(remoteId)
        localRepository.updateGiftList(listMapper.fromRemoteToEntity(remoteGiftList))
        return listMapper.fromRemoteToModel(remoteGiftList,
            remoteGiftList.gifts.map {giftMapper.fromRemoteToModel(it)})
    }

    override suspend fun deleteGiftList(remoteId: Long): Boolean {
        return try {
            localRepository.deleteGiftList(remoteId) &&
                    remoteRepository.deleteGiftList(remoteId)
        }
        catch (e: Exception) {
            false
        }
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
                    remoteRepository.updateGift(giftMapper.fromModelToRemote(gift))
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun createGift(gift: Gift, giftListId: Long) {
        try {
            localRepository.createGift(giftMapper.fromModelToEntity(gift, giftListId))
            remoteRepository.createGift(giftMapper.fromModelToRemote(gift))
        }
        catch (_: Exception) {
        }
    }
}