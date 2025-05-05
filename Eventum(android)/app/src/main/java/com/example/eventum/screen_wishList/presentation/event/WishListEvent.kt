package com.example.eventum.screen_wishList.presentation.event

import com.example.eventum.screen_presents.domain.model.Present

sealed class WishListEvent {
    class ChangeOrderEvent: WishListEvent()
    class UpdatePresent(val present: Present): WishListEvent()
    class CreatePresent(val present: Present): WishListEvent()
    class DeletePresent(val present: Present): WishListEvent()
    class ChangeVisibility: WishListEvent()
}