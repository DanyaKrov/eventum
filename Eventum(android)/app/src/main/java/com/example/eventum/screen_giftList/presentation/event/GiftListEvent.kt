package com.example.eventum.screen_giftList.presentation.event

import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.model.GiftRequestModel

sealed class GiftListEvent {
    class EditGiftEvent(val gift: Gift): GiftListEvent()
    class AddGiftEvent(val gift: GiftRequestModel): GiftListEvent()
    class DeleteGiftEvent(val gift: Gift): GiftListEvent()
}