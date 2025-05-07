package com.example.eventum.screen_giftList.data.service

import com.example.eventum.screen_giftList.data.local.repository.GiftListLocalRepository
import com.example.eventum.screen_giftList.data.remote.repository.GiftListRemoteRepository
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.repository.GiftListRepository
import com.example.eventum.screen_presents.data.local.repository.PresentsLocalRepository
import com.example.eventum.screen_presents.data.service.PresentsService
import com.example.eventum.screen_presents.domain.repository.PresentsRepository
import com.example.eventum.util.mapper.GiftMapper
import com.example.eventum.util.mapper.PresentMapper
import javax.inject.Inject

class GiftListService @Inject constructor(
    private val localRepository: GiftListLocalRepository,
    private val remoteRepository: GiftListRemoteRepository,
    private val presentsRepository: PresentsRepository,
    private val presentsLocalRepository: PresentsLocalRepository,
    private val giftMapper: GiftMapper,
    private val presentMapper: PresentMapper
    ): GiftListRepository {
    override suspend fun getGifts(contactId: Long, forceRefresh: Boolean): List<Gift> {
        if(forceRefresh) {
            updateLocalEntity(contactId)
        } else {
            val localEvents = localRepository.getGifts(contactId)
            if (localEvents.isEmpty()) {
                updateLocalEntity(contactId)
            }
        }

        return localRepository.getGifts(contactId).map {
            giftMapper.fromEntityToModel(giftEntity = it,
                present = it.presentRemoteId?.let { it1 -> presentsLocalRepository.getPresent(it1) }
                    ?.let { it2 ->
                        presentMapper.fromEntityToModel(
                            it2
                        )
                    },
                ) }
    }

    private suspend fun getModelFromLocalEntity(contactRemoteId: Long): List<Gift> {
        val localGifts = localRepository.getGifts(contactRemoteId)
        return localGifts.map {
            val present = presentsRepository.getPresent(it.presentRemoteId ?: 0)
            giftMapper.fromEntityToModel(present = present,
                giftEntity = it) // add gift state later on
        }
    }

    private suspend fun updateLocalEntity(contactId: Long) {
        val remoteGifts = remoteRepository.getGifts(contactId)
        localRepository.deleteAll()
        remoteGifts.forEach {
            localRepository.createCustomGift(giftMapper.fromRemoteToEntity(it))
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

    override suspend fun updateCustomGift(gift: Gift): Boolean {
        return try {
            presentsLocalRepository.updatePresent(presentMapper.fromGiftModelToEntity(gift)) &&
                    remoteRepository.updateCustomGift(gift.remoteId,
                        giftMapper.fromModelToCustomRequest(gift))
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun createCustomGift(gift: Gift, contactRemoteId: Long) {
        try {
            // first, create gift at remote database, get its remote id and remote id of present associated
            val createdGift = remoteRepository.createCustomGift(gift.contactId, giftMapper.fromModelToCustomRequest(gift))
            // then create local present in the image and likeness of remote present
            presentsLocalRepository.insert(presentMapper.fromGiftRemoteToEntity(createdGift))
            // at the end create local gift with a link on just created present
            localRepository.createCustomGift(giftMapper.fromModelToEntity(gift, contactRemoteId, createdGift.presentId))
        }
        catch (_: Exception) {
        }
    }
}