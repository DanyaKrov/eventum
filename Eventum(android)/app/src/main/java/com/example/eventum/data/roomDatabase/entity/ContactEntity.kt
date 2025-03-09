package com.example.eventum.data.roomDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.eventum.data.roomDatabase.converter.ListConverter

@Entity(tableName = "contact")
data class ContactEntity(
    @PrimaryKey val id: Long = 0,
    val remoteId: Long = 0, // remote id from mysql database
    val name: String,
    val authorisedStatus: Boolean = false, // is contact authorised or not
    val tag: String = ""
)
