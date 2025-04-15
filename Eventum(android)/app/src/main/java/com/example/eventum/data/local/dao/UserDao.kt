package com.example.eventum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.eventum.data.local.model.entity.UserEntity
import com.example.eventum.data.local.model.entity.UserCrossUserRef
import com.example.eventum.data.local.model.entity.UserWithUsers

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Query("DELETE FROM user")
    suspend fun delete()

    @Query("SELECT * FROM user WHERE remoteId=:remoteId")
    suspend fun get(remoteId: Long): UserEntity

    @Insert()
    suspend fun insertFriendShip(userFriendship: UserCrossUserRef)

    @Transaction
    @Query("SELECT * FROM user WHERE remoteId=:searchUserId")
    suspend fun getFriends(searchUserId: Long): UserWithUsers
}