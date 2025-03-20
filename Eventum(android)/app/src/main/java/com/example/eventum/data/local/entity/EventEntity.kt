package com.example.eventum.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "event",
    indices = [Index(value = ["eventId"], unique = true)])
data class EventEntity (
    @PrimaryKey val id: Long = 0,
    val eventId: Long, // id from mysql database
    val name: String,
    val description: String,
    val time: String, // "yyyy-MM-dd format
    val picture: String,
    val tag: String, // tag of event
    // list of contacts associated
)


data class EventWithNotificationsEntity(
    @Embedded val event: EventEntity,

    @Relation(
        parentColumn = "eventId",
        entityColumn = "eventOwnerId"
    )
    val notifications: List<NotificationEntity>
)