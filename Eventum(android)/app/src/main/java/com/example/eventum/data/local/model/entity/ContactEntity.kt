package com.example.eventum.data.local.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "contact",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["remoteId"],
            childColumns = ["userRemoteId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["remoteId"], unique = true), Index("userRemoteId")],
    primaryKeys = ["remoteId"])
data class ContactEntity(
    @ColumnInfo("remoteId") val remoteId: Long, // remote id from mysql database
    val name: String,
    val userRemoteId: Long,
    val authorisedStatus: Boolean = false, // is contact authorised or not
    val tag: String = ""
)
