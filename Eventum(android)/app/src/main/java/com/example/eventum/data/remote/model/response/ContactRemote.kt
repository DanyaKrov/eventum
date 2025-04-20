package com.example.eventum.data.remote.model.response

data class ContactRemote (
    var id: Long = 0,
    var name: String,
    val hostUserId: Long,
    val friendUser: UserContactRemote?, // is contact based on authorised user
    val gifts: List<Long>
)