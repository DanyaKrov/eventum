package com.example.eventum.screen_presents.domain.model

data class Present(
    val id: Long = 0,
    val remoteId: Long = 0, // remote id from mysql database
    val title: String,
    val description: String,
    val wishListId: Long
)
