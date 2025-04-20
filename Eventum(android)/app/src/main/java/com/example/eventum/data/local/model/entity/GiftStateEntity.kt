package com.example.eventum.data.local.model.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "giftState",
    indices = [Index(value = ["remoteId"], unique = true)]
)
data class GiftStateEntity(
    @PrimaryKey val id: Long = 0,
    val remoteId: Long,
    val name: String
)
