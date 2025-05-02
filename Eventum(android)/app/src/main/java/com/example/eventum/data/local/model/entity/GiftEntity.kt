package com.example.eventum.data.local.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "gift",
    foreignKeys = [
        ForeignKey(
            entity = ContactEntity::class,
            parentColumns = ["remoteId"],
            childColumns = ["contactRemoteId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PresentEntity::class,
            parentColumns = ["remoteId"],
            childColumns = ["presentRemoteId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GiftStateEntity::class,
            parentColumns = ["remoteId"],
            childColumns = ["stateRemoteId"],
        )
    ],
    indices = [Index(value = ["remoteId"], unique = true), Index("presentRemoteId"),
        Index("contactRemoteId"), Index("stateRemoteId")])
data class GiftEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val remoteId: Long,
    val presentRemoteId: Long?, // id of present based on which gift was created
    val contactRemoteId: Long, // id of contact to who belongs gift
    val stateRemoteId: Long?, // remote id of gift state
)