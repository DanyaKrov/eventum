package com.example.eventum.screen_giftList.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.eventum.data.local.entity.GiftListEntity
import com.example.eventum.data.local.entity.GiftListWithGifts
import com.example.eventum.data.local.entity.WishListWithPresents

@Dao
interface GiftListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(giftList: GiftListEntity)

    @Update
    suspend fun update(giftList: GiftListEntity)


    @Query("DELETE FROM giftList WHERE remoteId=:giftListRemoteId")
    suspend fun delete(giftListRemoteId: Long)

    @Query("SELECT * FROM giftList WHERE remoteId=:giftListRemoteId")
    suspend fun get(giftListRemoteId: Long): GiftListEntity

    @Transaction
    @Query("SELECT * FROM giftList WHERE remoteId=:giftListRemoteId")
    suspend fun getGiftListWithGifts(giftListRemoteId: Long): GiftListWithGifts
}