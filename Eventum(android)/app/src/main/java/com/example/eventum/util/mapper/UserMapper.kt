package com.example.eventum.util.mapper

import com.example.eventum.data.remote.model.UserResponse
import com.example.eventum.data.local.entity.UserEntity
import com.example.eventum.domain.model.User
import dagger.internal.DaggerGenerated

@DaggerGenerated
class UserMapper {
    fun fromEntityToModel(user: UserEntity): User = User(
        localId = user.id,
        remoteId = user.userId,
        name = user.name,
        email = user.email,
        picture = user.picture
    )

    fun createEntity(user: UserResponse): UserEntity = UserEntity(
        userId = user.id,
        name = user.name,
        email = user.email,
        picture = user.picture,
        friends = user.friends,
        events = user.events
    )
}