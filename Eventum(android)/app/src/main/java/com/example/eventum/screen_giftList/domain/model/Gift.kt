package com.example.eventum.screen_giftList.domain.model

import com.example.eventum.data.remote.model.GiftListRemote
import com.example.eventum.data.remote.model.GiftStateRemote

data class Gift(
    val remoteId: Long,
    val presentId: Long?,
    val stateId: Long,
    val title: String,
    val description: String
    // val giftListId: Long, no need, because giftList always contains his gifts
)