package com.example.eventum.data.roomDatabase.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.example.eventum.data.api.model.UserResponse
import com.example.eventum.data.roomDatabase.converter.ListConverter

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Long = 0,
    val userId: Long, // id from mysql database
    val name: String,
    val email: String,
    val picture: String,
    @TypeConverters(ListConverter::class)
    val friends: List<Long>,
    @TypeConverters(ListConverter::class)
    val events: List<Long> = listOf()
)