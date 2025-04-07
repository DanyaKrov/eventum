package com.example.eventum.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.eventum.screen_giftList.domain.model.GiftList

@Entity(tableName = "giftList",
    indices = [Index(value = ["remoteId"], unique = true)]) // need to make this field unique
data class GiftListEntity (
    @PrimaryKey val id: Long = 0,
    val remoteId: Long, // id from mysql database
    val contactId: Long
)


data class GiftListWithGifts(
    @Embedded val giftList: GiftListEntity,
    @Relation(
        parentColumn = "remoteId",
        entityColumn = "giftListId"
    )
    val gifts: List<GiftEntity>
)