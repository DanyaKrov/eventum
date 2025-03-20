package com.example.eventum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eventum.data.local.entity.ContactEntity

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: ContactEntity)

    @Update
    suspend fun update(contact: ContactEntity)


    @Query("DELETE FROM contact WHERE id=:id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM contact WHERE id=:id")
    suspend fun get(id: Long): ContactEntity

    @Query("DELETE FROM contact")
    suspend fun deleteAll()

    @Query("SELECT * FROM contact")
    suspend fun getAll(): List<ContactEntity>
}