package com.example.eventum.data.remote.model.response

data class GiftRemoteResponse (
    val id: Long,
    val presentId: Long,
    val presentTitle: String,
    val presentDescription: String,
    val contactId: Long,
    val stateId: Long,
    val giftCount: Long?
)