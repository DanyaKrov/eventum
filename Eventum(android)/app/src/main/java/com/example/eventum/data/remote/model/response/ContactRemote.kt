package com.example.eventum.data.remote.model.response

data class ContactRemote (
    var id: Long = 0,
    var name: String,
    val hostUser: UserRemote,
    val friendUser: UserRemote?, // is contact based on authorised user
    // var giftList: GiftList will make infinite dependency
)