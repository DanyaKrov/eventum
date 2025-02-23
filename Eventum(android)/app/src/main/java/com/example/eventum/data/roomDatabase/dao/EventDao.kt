package com.example.eventum.data.roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eventum.data.roomDatabase.entity.EventEntity
import com.example.eventum.data.roomDatabase.entity.UserEntity

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventEntity)

    @Update
    suspend fun update(event: EventEntity)


    @Query("DELETE FROM event WHERE eventId=:id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM event WHERE eventId=:id")
    suspend fun get(id: Long): EventEntity

    @Query("DELETE FROM event")
    suspend fun deleteAll()

    @Query("SELECT * FROM event")
    suspend fun getAll(): List<EventEntity>
}