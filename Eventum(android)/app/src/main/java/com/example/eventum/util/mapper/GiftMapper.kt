package com.example.eventum.util.mapper

import com.example.eventum.data.local.entity.GiftEntity
import com.example.eventum.data.local.entity.GiftListEntity
import com.example.eventum.data.remote.model.GiftListRemote
import com.example.eventum.data.remote.model.GiftRemote
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.model.GiftList
import dagger.internal.DaggerGenerated

@DaggerGenerated
class GiftMapper {
    fun fromRemoteToModel(giftRemote: GiftRemote): Gift =
        Gift(
            remoteId = giftRemote.id,
            presentId = giftRemote.present?.id,
            stateId = giftRemote.state?.id ?: 0, // I will correct it, when I will have a better vision of gift state
            title = giftRemote.title,
            description = giftRemote.description
        )

    fun fromEntityToModel(giftEntity: GiftEntity): Gift =
        Gift(
            remoteId = giftEntity.remoteId,
            presentId = giftEntity.presentId,
            stateId = giftEntity.stateId,
            title = giftEntity.title,
            description = giftEntity.description
        )

    fun fromModelToEntity(gift: Gift, giftListId: Long): GiftEntity =
        GiftEntity(
            remoteId = gift.remoteId,
            presentId = gift.presentId,
            stateId = gift.stateId,
            title = gift.title,
            description = gift.description,
            giftListId = giftListId
        )

    fun fromModelToRemote(gift: Gift): GiftRemote =
        GiftRemote(
            id = gift.remoteId,
            title = gift.title,
            description = gift.description
        )
}