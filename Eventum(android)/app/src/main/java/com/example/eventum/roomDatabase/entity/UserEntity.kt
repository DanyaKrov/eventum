package com.example.eventum.roomDatabase.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.eventum.api.model.UserResponse

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val email: String,
    val picture: String,
)


@Entity(
    tableName = "user_friends",
    primaryKeys = ["userId", "friendId"],
    foreignKeys = [
        ForeignKey(entity = UserEntity::class, parentColumns = ["id"], childColumns = ["userId"]),
        ForeignKey(entity = UserEntity::class, parentColumns = ["id"], childColumns = ["friendId"])
    ]
)
data class UserFriendCrossRef(
    val userId: Long,
    val friendId: Long
)


data class UserFriends(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(UserFriendCrossRef::class)
    )
    val friends: List<UserEntity>
)