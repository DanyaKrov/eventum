package com.example.eventum.data.roomDatabase.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "wishList",
    indices = [Index(value = ["wishListId"], unique = true)]) // need to make this field unique
data class WishListEntity(
    @PrimaryKey val id: Long,
    val wishListId: Long, // id from mysql database

)


data class WishListWithPresents(
    @Embedded val user: WishListEntity,
    @Relation(
        parentColumn = "wishListId",
        entityColumn = "wishListParentId"
    )
    val presents: List<PresentEntity>
)
