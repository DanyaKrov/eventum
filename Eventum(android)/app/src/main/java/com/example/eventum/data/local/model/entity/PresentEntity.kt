package com.example.eventum.data.local.model.entity

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
    indices = [Index("wishListParentId"), Index(value = ["remoteId"], unique = true)]
)
data class PresentEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val remoteId: Long, // id of present from mysql database
    val wishListParentId: Long? = null, // id of wishList to whom belongs present
    val title: String,
    val description: String
)