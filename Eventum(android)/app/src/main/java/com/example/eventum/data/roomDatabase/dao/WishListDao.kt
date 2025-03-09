package com.example.eventum.data.roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.eventum.data.roomDatabase.entity.PresentEntity
import com.example.eventum.data.roomDatabase.entity.WishListEntity
import com.example.eventum.data.roomDatabase.entity.WishListWithPresents

@Dao
interface WishListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: WishListEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPresents(events: List<PresentEntity>)

    @Transaction
    @Query("SELECT * FROM wishList WHERE wishListId = :wishListId")
    suspend fun getUserWithEvents(wishListId: Long): WishListWithPresents
}