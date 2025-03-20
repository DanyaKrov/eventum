package com.example.eventum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.eventum.data.local.entity.EventEntity
import com.example.eventum.data.local.entity.EventWithNotificationsEntity

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventEntity)

    @Update
    suspend fun update(event: EventEntity)


    @Query("DELETE FROM event WHERE eventId=:id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM event WHERE eventId=:id")
    suspend fun getByRemoteId(id: Long): EventEntity

    @Query("DELETE FROM event")
    suspend fun deleteAll()

    @Query("SELECT * FROM event")
    suspend fun getAll(): List<EventEntity>

    @Transaction
    @Query("SELECT * FROM event WHERE eventId = :eventId")
    suspend fun getEventWithNotificationsEntity(eventId: Long): EventWithNotificationsEntity
}