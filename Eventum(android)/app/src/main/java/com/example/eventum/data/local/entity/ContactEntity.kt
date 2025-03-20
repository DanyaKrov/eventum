package com.example.eventum.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class ContactEntity(
    @PrimaryKey val id: Long = 0,
    val remoteId: Long = 0, // remote id from mysql database
    val name: String,
    val authorisedStatus: Boolean = false, // is contact authorised or not
    val tag: String = ""
)
