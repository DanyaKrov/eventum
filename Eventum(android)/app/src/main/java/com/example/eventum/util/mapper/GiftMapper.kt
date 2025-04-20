package com.example.eventum.util.mapper

import com.example.eventum.data.local.model.entity.GiftEntity
import com.example.eventum.data.local.model.entity.GiftStateEntity
import com.example.eventum.data.local.model.entity.PresentEntity
import com.example.eventum.data.remote.model.request.CustomGiftRemoteRequest
import com.example.eventum.data.remote.model.response.GiftRemoteResponse
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.model.GiftState
import com.example.eventum.screen_presents.domain.model.Present
import dagger.internal.DaggerGenerated

@DaggerGenerated
class GiftMapper {
    fun fromRemoteToModel(giftRemote: GiftRemoteResponse, giftStateModel: GiftState? = null): Gift =
        Gift(
            remoteId = giftRemote.id,
            presentId = giftRemote.presentId,
            giftState = giftStateModel,
            contactId = giftRemote.contactId,
            giftCount = giftRemote.giftCount,
            presentDescription = giftRemote.presentDescription,
            presentTitle = giftRemote.presentTitle
        )

    fun fromEntityToModel(present: Present, giftEntity: GiftEntity, giftStateModel: GiftState? = null): Gift =
        Gift(
            remoteId = giftEntity.remoteId,
            presentId = present.remoteId,
            giftState = giftStateModel,
            presentTitle = present.title,
            presentDescription = present.description,
            contactId = giftEntity.contactRemoteId,
            giftCount = null // later on I will ad counting for gifts
        )

    fun fromModelToEntity(gift: Gift, contactRemoteId: Long): GiftEntity =
        GiftEntity(
            remoteId = gift.remoteId,
            presentRemoteId = gift.presentId,
            stateRemoteId = gift.giftState?.remoteId,
            contactRemoteId = contactRemoteId
        )

    fun fromRemoteToEntity(gift: GiftRemoteResponse): GiftEntity =
        GiftEntity(
            remoteId = gift.id,
            presentRemoteId = gift.presentId,
            stateRemoteId = gift.stateId,
            contactRemoteId = gift.contactId
        )

    fun fromModelToCustomRequest(gift: Gift): CustomGiftRemoteRequest =
        CustomGiftRemoteRequest(
            title = gift.presentTitle,
            description = gift.presentDescription
        )
}