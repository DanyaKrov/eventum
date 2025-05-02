package com.example.eventum.util.mapper

import com.example.eventum.data.remote.model.response.UserRemote
import com.example.eventum.data.local.model.entity.UserEntity
import com.example.eventum.domain.model.User
import dagger.internal.DaggerGenerated

@DaggerGenerated
class UserMapper {
    fun fromEntityToModel(user: UserEntity): User = User(
        remoteId = user.remoteId,
        name = user.name,
        email = user.email,
        picture = user.picture
    )

    fun fromRemoteToModel(user: UserRemote): User = User(
        remoteId = user.id,
        name = user.name,
        email = user.email,
        picture = user.picture
    )

    fun createEntity(user: UserRemote): UserEntity = UserEntity(
        remoteId = user.id,
        name = user.name,
        email = user.email,
        picture = user.picture
    )

    fun fromModelToEntity(user: User) = UserEntity(
        remoteId = user.remoteId,
        name = user.name,
        email = user.email,
        picture = user.picture
    )

    fun fromRemoteToEntity(user: UserRemote) = UserEntity(
        remoteId = user.id,
        name = user.name,
        email = user.email,
        picture = user.picture
    )

    fun fromModelToRemoteEntity(user: User) = UserRemote(
        id = user.remoteId,
        name = user.name,
        email = user.email,
        picture = user.picture
    )
}