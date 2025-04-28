package com.example.eventum.screen_giftList.domain.model

data class Gift(
    val remoteId: Long = 0,
    val presentId: Long? = null,
    val presentTitle: String,
    val presentDescription: String,
    val contactId: Long,
    val giftState: GiftState?,
    val giftCount: Long?
)