package com.example.eventum.util.mapper

import com.example.eventum.data.local.entity.WishListEntity
import com.example.eventum.data.local.entity.WishListWithPresents
import com.example.eventum.data.remote.model.WishListResponse
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_wishList.domain.model.WishList
import dagger.internal.DaggerGenerated

@DaggerGenerated
class WishListMapper {
    fun fromResponseToEntity(wishList: WishListResponse): WishListEntity =
        WishListEntity(
            remoteId = wishList.id,
            userId = wishList.userId
        )


    fun fromResponseToModel(wishList: WishListResponse): WishList =
        WishList(
            remoteId = wishList.id,
            presents = wishList.presents,
            userId = wishList.userId
        )

    fun fromEntityToModel(wishList: WishListEntity, presents: List<Present>): WishList =
        WishList(
            remoteId = wishList.id,
            presents = presents,
            userId = wishList.userId
        )

    fun fromModelToResponse(wishList: WishList): WishListResponse =
        WishListResponse(
            id = wishList.remoteId,
            userId = wishList.userId,
            presents = wishList.presents
        )

    fun fromModelToEntity(wishList: WishList): WishListEntity =
        WishListEntity(
            remoteId = wishList.remoteId,
            userId = wishList.userId
        )
}