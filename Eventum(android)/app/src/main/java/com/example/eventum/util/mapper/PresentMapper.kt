package com.example.eventum.util.mapper

import com.example.eventum.data.local.model.entity.PresentEntity
import com.example.eventum.data.remote.model.request.PresentRemoteRequest
import com.example.eventum.data.remote.model.response.GiftRemoteResponse
import com.example.eventum.data.remote.model.response.PresentRemoteResponse
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_presents.domain.model.Present
import dagger.internal.DaggerGenerated

@DaggerGenerated
class PresentMapper {
    fun fromEntityToModel(presentEntity: PresentEntity): Present = Present(
        id = presentEntity.id,
        title = presentEntity.title,
        description = presentEntity.description,
        wishListId = presentEntity.wishListParentId
    )

    fun fromModelToEntity(present: Present, remoteId: Long): PresentEntity = PresentEntity(
        remoteId = remoteId,
        title = present.title,
        description = present.description,
        wishListParentId = present.wishListId
    )

    fun fromGiftRemoteToEntity(gift: GiftRemoteResponse): PresentEntity = PresentEntity(
        remoteId = gift.presentId,
        title = gift.presentTitle,
        description = gift.presentDescription
    )

    fun fromGiftModelToEntity(gift: Gift): PresentEntity = PresentEntity(
        remoteId = gift.presentId!!, // maybe will do it in better way
        title = gift.presentTitle,
        description = gift.presentDescription
    )

    fun fromRemoteToModel(present: PresentRemoteResponse): Present = Present(
        id = present.id,
        title = present.title,
        description = present.description
    )

    fun fromModelToRemoteRequest(present: Present): PresentRemoteRequest = PresentRemoteRequest(
        title = present.title,
        description = present.description
    )

    fun fromRemoteToEntity(present: PresentRemoteResponse): PresentEntity = PresentEntity(
        remoteId = present.id,
        title = present.title,
        description = present.description
    )

    fun updateEntity(oldPresent: PresentEntity, newPresent: Present): PresentEntity = PresentEntity(
        newPresent.id,
        oldPresent.remoteId,
        oldPresent.wishListParentId,
        newPresent.title,
        newPresent.description
    )
}