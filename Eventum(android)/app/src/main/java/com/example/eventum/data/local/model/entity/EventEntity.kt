package com.example.eventum.data.local.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "event",
    indices = [Index(value = ["remoteId"], unique = true), Index("userRemoteId")],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["remoteId"],
            childColumns = ["userRemoteId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class EventEntity (
    @PrimaryKey val id: Long = 0,
    val remoteId: Long, // id from mysql database
    val name: String,
    val description: String,
    val time: String, // "yyyy-MM-dd format
    val picture: String,
    val tag: String, // tag of event
    val userRemoteId: Long
    // list of contacts associated
)