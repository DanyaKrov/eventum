package com.example.eventum.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.eventum.data.local.converter.ListConverter

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Long = 0,
    val remoteId: Long, // id from mysql database
    val name: String,
    val email: String,
    val picture: String,
    @TypeConverters(ListConverter::class)
    val friends: List<Long> = listOf(),
    @TypeConverters(ListConverter::class)
    val events: List<Long> = listOf()
)