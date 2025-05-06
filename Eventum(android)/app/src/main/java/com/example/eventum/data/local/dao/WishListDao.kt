package com.example.eventum.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.eventum.data.local.model.entity.PresentEntity
import com.example.eventum.data.local.model.entity.WishListEntity
import com.example.eventum.data.local.model.entity.WishListWithPresents

@Dao
interface WishListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishList(user: WishListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPresents(presents: List<PresentEntity>)

    @Update
    suspend fun updateWishList(wishList: WishListEntity)

    @Update
    suspend fun updatePresents(presents: List<PresentEntity>)

    @Query("DELETE FROM wishList WHERE remoteId=:remoteId")
    suspend fun delete(remoteId: Long)

    @Transaction
    suspend fun updateWishListWithPresents(wishList: WishListEntity, presents: List<PresentEntity>) {
        updateWishList(wishList)
        updatePresents(presents)
    }

    @Transaction
    @Query("SELECT * FROM wishList WHERE remoteId=:wishListRemoteId")
    suspend fun getWishListWithPresents(wishListRemoteId: Long): WishListWithPresents
}