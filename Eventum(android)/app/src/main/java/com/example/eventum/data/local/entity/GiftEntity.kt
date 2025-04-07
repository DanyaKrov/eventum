package com.example.eventum.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "gift",
    foreignKeys = [
        ForeignKey(
            entity = GiftListEntity::class,
            parentColumns = ["remoteId"],
            childColumns = ["giftListId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("giftListId")])
data class GiftEntity (
    @PrimaryKey val id: Long = 0,
    val remoteId: Long,
    val giftListId: Long, // id of giftList to whom belongs present
    val presentId: Long?, // id of present based on which gift was created
    val stateId: Long, // remote id of gift state
    val title: String,
    val description: String
)