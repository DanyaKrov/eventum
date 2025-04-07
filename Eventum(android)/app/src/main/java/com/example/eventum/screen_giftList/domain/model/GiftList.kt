package com.example.eventum.screen_giftList.domain.model

import com.example.eventum.data.remote.model.ContactRemote

data class GiftList (
    val remoteId: Long,
    val contactId: Long,
    val gifts: List<Gift>
)