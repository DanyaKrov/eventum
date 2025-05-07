package com.example.eventum.util.mapper

import com.example.eventum.data.local.model.entity.WishListEntity
import com.example.eventum.data.remote.model.request.WishListRemoteRequest
import com.example.eventum.data.remote.model.response.PresentRemoteResponse
import com.example.eventum.data.remote.model.response.WishListRemoteResponse
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_wishList.domain.model.WishList
import dagger.internal.DaggerGenerated

@DaggerGenerated
class WishListMapper {
    fun fromResponseToEntity(wishList: WishListRemoteResponse, userId: Long): WishListEntity =
        WishListEntity(
            remoteId = wishList.id,
            userId = userId
        )

    fun fromEntityToModel(wishList: WishListEntity, presents: List<Present>, visibility: Boolean): WishList =
        WishList(
            remoteId = wishList.id,
            presents = presents,
            userId = wishList.userId,
            visibility = visibility
        )

    fun fromModelToRemoteRequest(newAvailability: Boolean): WishListRemoteRequest =
        WishListRemoteRequest(
            isAvailable = newAvailability
        )
    fun fromModelToEntity(wishList: WishList): WishListEntity =
        WishListEntity(
            remoteId = wishList.remoteId,
            userId = wishList.userId
        )
}