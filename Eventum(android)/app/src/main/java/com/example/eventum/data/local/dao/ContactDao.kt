package com.example.eventum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eventum.data.local.model.entity.ContactEntity

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: ContactEntity)

    @Update
    suspend fun update(contact: ContactEntity)


    @Query("DELETE FROM contact WHERE remoteId=:remoteId")
    suspend fun delete(remoteId: Long)

    @Query("SELECT * FROM contact WHERE remoteId=:remoteId")
    suspend fun get(remoteId: Long): ContactEntity

    @Query("DELETE FROM contact")
    suspend fun deleteAll()

    @Query("SELECT * FROM contact WHERE userRemoteId=:userId")
    suspend fun getAll(userId: Long): List<ContactEntity>
}