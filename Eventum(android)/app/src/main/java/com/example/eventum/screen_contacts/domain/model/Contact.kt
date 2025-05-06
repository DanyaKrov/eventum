package com.example.eventum.screen_contacts.domain.model

data class Contact(
    val remoteId: Long = 0, // remote id from mysql database
    val userRemoteId: Long,
    val name: String,
    val picture: String = "",
    val authorisedStatus: Boolean = false, // is contact authorised or not
    val tag: String = ""
)
