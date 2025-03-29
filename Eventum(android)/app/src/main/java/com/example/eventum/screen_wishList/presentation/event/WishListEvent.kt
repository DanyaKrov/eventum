package com.example.eventum.screen_wishList.presentation.event

import com.example.eventum.screen_presents.domain.model.Present

sealed class WishListEvent {
    class ChangeOrderEvent: WishListEvent()
    class EditPresentEvent(val present: Present): WishListEvent()
    class CreatePresentEvent: WishListEvent()
    class ChangeVisibility: WishListEvent()
    class CreateWishList: WishListEvent()
}