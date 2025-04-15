package com.example.eventum.util.mapper

import com.example.eventum.data.local.model.entity.PresentEntity
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

    fun fromModelToEntity(present: Present): PresentEntity = PresentEntity(
        presentId = present.id,
        title = present.title,
        description = present.description,
        wishListParentId = present.wishListId
    )

    fun updateEntity(oldPresent: PresentEntity, newPresent: Present): PresentEntity = PresentEntity(
        newPresent.id,
        oldPresent.presentId,
        oldPresent.wishListParentId,
        newPresent.title,
        newPresent.description
    )
}