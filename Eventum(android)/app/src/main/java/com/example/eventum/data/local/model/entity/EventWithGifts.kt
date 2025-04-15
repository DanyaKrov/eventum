package com.example.eventum.data.local.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Junction
import androidx.room.Relation


data class EventWithGifts(
    @Embedded val event: EventEntity,
    @Relation(
        parentColumn = "remoteId",
        entityColumn = "remoteId",
        associateBy = Junction(EventGiftsCrossRef::class)
    )
    val gifts: List<GiftEntity>
)


@Entity(
    tableName = "event_cross_gifts",
    primaryKeys = ["eventId", "giftId"],
    foreignKeys = [
        ForeignKey(entity = EventEntity::class, parentColumns = ["remoteId"], childColumns = ["eventId"]),
        ForeignKey(entity = GiftEntity::class, parentColumns = ["remoteId"], childColumns = ["giftId"])
    ],
    indices = [Index("eventId"), Index("giftId")]
)
data class EventGiftsCrossRef(
    val eventId: Long,
    val giftId: Long
)