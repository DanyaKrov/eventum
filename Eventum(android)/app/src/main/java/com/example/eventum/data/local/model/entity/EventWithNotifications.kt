package com.example.eventum.data.local.model.entity

import androidx.room.Embedded
import androidx.room.Relation

data class EventWithNotificationsEntity(
    @Embedded val event: EventEntity,

    @Relation(
        parentColumn = "remoteId",
        entityColumn = "eventOwnerId"
    )
    val notifications: List<NotificationEntity>
)
