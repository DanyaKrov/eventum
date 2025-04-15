package com.example.eventum.data.local.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "notification",
    foreignKeys = [ForeignKey(
        entity = EventEntity::class,
        parentColumns = ["remoteId"],
        childColumns = ["eventOwnerId"],
        onDelete = ForeignKey.CASCADE)],
    indices = [Index("eventOwnerId")]
)
data class NotificationEntity (
    @PrimaryKey val id: Long = 0,
    val requestId: String, // id from workManager in order to cancel notification if need
    val title: String,
    val description: String,
    val time: String, // "yyyy-MM-dd format
    val eventOwnerId: Long,
)