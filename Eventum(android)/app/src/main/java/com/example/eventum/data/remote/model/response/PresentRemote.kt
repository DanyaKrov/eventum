package com.example.eventum.data.remote.model.response

data class PresentRemote (
    val id: Long = 0,
    val title: String,
    val description: String,
    val wishlist: WishListRemote?
)