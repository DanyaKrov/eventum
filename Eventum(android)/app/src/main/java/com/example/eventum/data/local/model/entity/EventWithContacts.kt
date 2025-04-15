package com.example.eventum.data.local.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Junction
import androidx.room.Relation


data class EventWithContacts(
    @Embedded val event: EventEntity,
    @Relation(
        parentColumn = "remoteId",
        entityColumn = "remoteId",
        associateBy = Junction(EventContactsCrossRef::class)
    )
    val contacts: List<ContactEntity>
)


@Entity(
    tableName = "event_cross_contacts",
    primaryKeys = ["eventId", "contactId"],
    foreignKeys = [
        ForeignKey(entity = EventEntity::class, parentColumns = ["remoteId"], childColumns = ["eventId"]),
        ForeignKey(entity = ContactEntity::class, parentColumns = ["remoteId"], childColumns = ["contactId"])
    ],
    indices = [Index("eventId"), Index("contactId")]
)
data class EventContactsCrossRef(
    val eventId: Long,
    val contactId: Long
)