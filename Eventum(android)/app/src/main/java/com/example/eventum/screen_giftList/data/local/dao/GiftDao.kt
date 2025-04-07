package com.example.eventum.screen_giftList.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eventum.data.local.entity.GiftEntity

@Dao
interface GiftDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gift: GiftEntity)

    @Update
    suspend fun update(gift: GiftEntity)


    @Query("DELETE FROM gift WHERE remoteId=:giftRemoteId")
    suspend fun delete(giftRemoteId: Long)

    @Query("SELECT * FROM gift WHERE remoteId=:giftRemoteId")
    suspend fun get(giftRemoteId: Long): GiftEntity
}