package com.example.eventum.data.local.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "gift")
data class GiftEntity (
    @PrimaryKey val id: Long = 0,
    val remoteId: Long,
    val presentId: Long?, // id of present based on which gift was created
    val contactRemoteId: Long, // id of contact to who belongs gift
    val stateId: Long, // remote id of gift state
    val title: String,
    val description: String
)