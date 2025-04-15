package com.example.eventum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eventum.data.local.model.entity.PresentEntity

@Dao
interface PresentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(present: PresentEntity)

    @Update
    suspend fun update(present: PresentEntity)


    @Query("DELETE FROM present WHERE id=:id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM present WHERE id=:id")
    suspend fun get(id: Long): PresentEntity

    @Query("DELETE FROM present WHERE wishListParentId=:wishListId")
    suspend fun deleteAll(wishListId: Long)

    @Query("SELECT * FROM present WHERE wishListParentId=:wishListId")
    suspend fun getAll(wishListId: Long): List<PresentEntity>
}