package com.example.eventum.data.local.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "wishList",
    indices = [Index(value = ["remoteId"], unique = true)]) // need to make this field unique
data class WishListEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val remoteId: Long, // id from mysql database
    val userId: Long
)

data class WishListWithPresents(
    @Embedded val wishList: WishListEntity,
    @Relation(
        parentColumn = "remoteId",
        entityColumn = "wishListParentId"
    )
    val presents: List<PresentEntity>
)
