package com.example.eventum.util.mapper

import com.example.eventum.data.local.entity.GiftEntity
import com.example.eventum.data.local.entity.GiftListEntity
import com.example.eventum.data.local.entity.GiftListWithGifts
import com.example.eventum.data.remote.model.GiftListRemote
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.model.GiftList
import dagger.internal.DaggerGenerated

@DaggerGenerated
class GiftListMapper {
    fun fromRemoteToEntity(giftListRemote: GiftListRemote): GiftListEntity =
        GiftListEntity(
            remoteId = giftListRemote.id,
            contactId = giftListRemote.contact.id
        )

    fun fromRemoteToModel(giftListRemote: GiftListRemote, gifts: List<Gift>): GiftList =
        GiftList(
            remoteId = giftListRemote.id,
            contactId = giftListRemote.contact.id,
            gifts = gifts
        )

    fun fromEntityToModel(giftListEntity: GiftListEntity, gifts: List<Gift>): GiftList =
        GiftList(
            remoteId = giftListEntity.remoteId,
            contactId = giftListEntity.contactId,
            gifts = gifts
        )
}