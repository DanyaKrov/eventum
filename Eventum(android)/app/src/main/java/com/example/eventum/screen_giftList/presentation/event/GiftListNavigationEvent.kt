package com.example.eventum.screen_giftList.presentation.event

sealed class GiftListNavigationEvent {
    class MoveBack(): GiftListNavigationEvent()
}