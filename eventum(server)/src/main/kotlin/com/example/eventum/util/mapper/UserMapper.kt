package com.example.eventum.util.mapper

import com.example.eventum.database.entity.User
import com.example.eventum.model.request.UserRequest
import com.example.eventum.model.response.UserResponse
import com.example.eventum.util.cipher.HashPasswordService
import org.springframework.stereotype.Component

@Component
class UserMapper(
    private val passwordService: HashPasswordService
) {
    fun entityToResponse(entity: User): UserResponse {
        return UserResponse(entity.id,
            entity.name,
            entity.email,
            entity.picture,
            entity.password,
            entity.friends.map { it.id },
            entity.events.map { it.id}
        )
    }

    fun updateUser(user: User, newUser: UserRequest): User = user.apply {
        this.email = newUser.email
        this.name = newUser.name
        this.picture = newUser.picture
    }

    fun createUser(user: UserRequest): User = User(
        name = user.name,
        email = user.email,
        picture = user.picture,
        password = passwordService.hashPassword(user.password) // hashing password
    )
}