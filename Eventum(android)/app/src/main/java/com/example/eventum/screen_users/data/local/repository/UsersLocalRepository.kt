package com.example.eventum.screen_users.data.local.repository

import com.example.eventum.data.local.model.entity.ContactEntity
import com.example.eventum.data.local.model.entity.UserEntity

interface UsersLocalRepository {
    suspend fun addFriend(senderUserId: Long, receiverUserId: Long)
    suspend fun saveUser(user: UserEntity)
    suspend fun saveAuthorisedContact(contact: ContactEntity): Boolean
}