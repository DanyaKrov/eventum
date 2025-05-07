package com.example.eventum.screen_contacts.domain.model

data class Contact(
    val remoteId: Long, // remote id from mysql database
    val userRemoteId: Long,
    val name: String,
    val picture: String = "",
    val userLogin: String? = null, // is contact authorised or not
    val tag: String = ""
)
