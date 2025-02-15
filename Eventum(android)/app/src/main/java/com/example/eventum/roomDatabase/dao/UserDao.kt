package com.example.eventum.roomDatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.eventum.roomDatabase.entity.UserEntity
import com.example.eventum.roomDatabase.entity.UserFriendCrossRef
import com.example.eventum.roomDatabase.entity.UserFriends

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(users: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFriendCrossRef(crossRef: UserFriendCrossRef)

    @Transaction
    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun getUser(userId: Long): UserEntity
}