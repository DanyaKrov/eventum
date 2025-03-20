package com.example.eventum.util.mapper

import com.example.eventum.data.local.entity.PresentEntity
import com.example.eventum.screen_presents.domain.model.Present
import dagger.internal.DaggerGenerated

@DaggerGenerated
class PresentMapper {
    fun fromEntityToModel(presentEntity: PresentEntity): Present = Present(
        id = presentEntity.id,
        title = presentEntity.title,
        description = presentEntity.description
    )

    fun updateEntity(oldPresent: PresentEntity, newPresent: Present): PresentEntity = PresentEntity(
        newPresent.id,
        oldPresent.presentId,
        oldPresent.wishListParentId,
        newPresent.title,
        newPresent.description
    )
}