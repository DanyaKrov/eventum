package com.example.eventum.data.roomDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
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