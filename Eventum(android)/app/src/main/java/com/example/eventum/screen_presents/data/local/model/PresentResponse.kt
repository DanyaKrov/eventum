package com.example.eventum.screen_presents.data.local.model

import androidx.room.PrimaryKey

data class PresentResponse(
    @PrimaryKey val id: Long,
    val presentId: Long,
    val wishListParentId: Long,
    val title: String,
    val description: String
)