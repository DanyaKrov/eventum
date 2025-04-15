package com.example.eventum.screen_giftList.domain.model

data class Gift(
    val remoteId: Long,
    val presentId: Long?,
    val stateId: Long,
    val title: String,
    val contactRemoteId: Long,
    val description: String
    // val giftListId: Long, no need, because giftList always contains his gifts
)