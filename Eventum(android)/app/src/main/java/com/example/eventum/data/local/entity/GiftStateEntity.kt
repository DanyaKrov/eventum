package com.example.eventum.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "giftState")
data class GiftStateEntity(
    @PrimaryKey val id: Long = 0,
    val remoteId: Long,
    val name: String
)
