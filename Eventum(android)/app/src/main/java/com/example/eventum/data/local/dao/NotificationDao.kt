package com.example.eventum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eventum.data.local.entity.EventEntity
import com.example.eventum.data.local.entity.NotificationEntity

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: NotificationEntity)

    @Update
    suspend fun update(notification: NotificationEntity)


    @Query("DELETE FROM notification WHERE id=:id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM notification WHERE id=:id")
    suspend fun get(id: Long): NotificationEntity

    @Query("DELETE FROM notification")
    suspend fun deleteAll()

    @Query("SELECT * FROM notification WHERE eventOwnerId=:eventId")
    suspend fun getAll(eventId: Long): List<NotificationEntity>
}