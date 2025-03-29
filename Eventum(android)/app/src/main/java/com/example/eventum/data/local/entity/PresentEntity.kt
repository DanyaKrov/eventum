package com.example.eventum.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "present",
    foreignKeys = [
        ForeignKey(
            entity = WishListEntity::class,
            parentColumns = ["remoteId"],
            childColumns = ["wishListParentId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("wishListParentId")]
)
data class PresentEntity (
    @PrimaryKey val id: Long = 0,
    val presentId: Long, // id of present from mysql database
    val wishListParentId: Long, // id of wishList to whom belongs present
    val title: String,
    val description: String
)