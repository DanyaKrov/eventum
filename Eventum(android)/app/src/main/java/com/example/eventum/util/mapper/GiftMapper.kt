package com.example.eventum.util.mapper

import com.example.eventum.data.local.model.entity.GiftEntity
import com.example.eventum.data.remote.model.response.GiftRemote
import com.example.eventum.screen_giftList.domain.model.Gift
import dagger.internal.DaggerGenerated

@DaggerGenerated
class GiftMapper {
    fun fromRemoteToModel(giftRemote: GiftRemote): Gift =
        Gift(
            remoteId = giftRemote.id,
            presentId = giftRemote.present?.id,
            stateId = giftRemote.state?.id ?: 0, // I will correct it, when I will have a better vision of gift state
            title = giftRemote.title,
            description = giftRemote.description,
            contactRemoteId = giftRemote.contactId
        )

    fun fromEntityToModel(giftEntity: GiftEntity): Gift =
        Gift(
            remoteId = giftEntity.remoteId,
            presentId = giftEntity.presentId,
            stateId = giftEntity.stateId,
            title = giftEntity.title,
            description = giftEntity.description,
            contactRemoteId = giftEntity.contactRemoteId
        )

    fun fromModelToEntity(gift: Gift, contactRemoteId: Long): GiftEntity =
        GiftEntity(
            remoteId = gift.remoteId,
            presentId = gift.presentId,
            stateId = gift.stateId,
            title = gift.title,
            description = gift.description,
            contactRemoteId = contactRemoteId
        )

    fun fromRemoteToEntity(gift: GiftRemote): GiftEntity =
        GiftEntity(
            remoteId = gift.id,
            presentId = gift.present?.id,
            stateId = gift.state?.id ?: 0,
            title = gift.title,
            description = gift.description,
            contactRemoteId =gift.contactId
        )

    fun fromModelToRemote(gift: Gift): GiftRemote =
        GiftRemote(
            id = gift.remoteId,
            title = gift.title,
            description = gift.description,
            contactId = gift.contactRemoteId
        )
}