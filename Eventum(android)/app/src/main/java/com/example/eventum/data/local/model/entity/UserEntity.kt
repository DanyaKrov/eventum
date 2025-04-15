package com.example.eventum.data.local.model.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "user",
    indices = [Index(value = ["remoteId"], unique = true)],
    primaryKeys = ["id", "remoteId"])
data class UserEntity(
    val id: Long = 0,
    @ColumnInfo("remoteId")
    val remoteId: Long, // id from mysql database
    val name: String,
    val email: String,
    val picture: String
)


@Entity(
    tableName = "User_with_Users",
    primaryKeys = ["userId", "friendUserId"],
    foreignKeys = [
        ForeignKey(entity = UserEntity::class, parentColumns = ["remoteId"], childColumns = ["userId"]),
        ForeignKey(entity = UserEntity::class, parentColumns = ["remoteId"], childColumns = ["friendUserId"])
    ],
    indices = [Index(value = ["userId"], unique = true), Index(value = ["friendUserId"], unique = true)]
)
data class UserCrossUserRef(
    val userId: Long,
    val friendUserId: Long
)

data class UserWithUsers(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "remoteId",
        entity = UserEntity::class,
        entityColumn = "remoteId",
        associateBy = Junction(
            value = UserCrossUserRef::class,
            parentColumn = "userId",
            entityColumn = "friendUserId"
        )
    )
    val friends: List<UserEntity>
)

data class UserWithContacts(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "remoteId",
        entityColumn = "userRemoteId"
    )
    val contacts: List<ContactEntity>
)

data class UserWithEvents(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "remoteId",
        entityColumn = "userRemoteId"
    )
    val events: List<EventEntity>
)