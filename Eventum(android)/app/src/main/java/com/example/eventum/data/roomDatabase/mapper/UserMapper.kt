package com.example.eventum.data.roomDatabase.mapper

import com.example.eventum.data.api.model.UserResponse
import com.example.eventum.data.roomDatabase.entity.UserEntity
import dagger.internal.DaggerGenerated


class UserMapper {
    fun createUser(user: UserResponse): UserEntity = UserEntity(
        userId = user.id,
        name = user.name,
        email = user.email,
        picture = user.picture,
        friends = user.friends,
        events = user.events
    )
}