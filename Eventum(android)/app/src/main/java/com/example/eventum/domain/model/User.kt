package com.example.eventum.domain.model

data class User(
    val localId: Long = 0,
    val remoteId: Long, // id from mysql database
    val name: String,
    val email: String,
    val picture: String = "",
    val friends: List<Long> = listOf(),
    val events: List<Long> = listOf()
)
