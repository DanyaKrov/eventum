package com.example.eventum.screen_users.data.remote.repository

import com.example.eventum.data.remote.model.response.ContactRemote
import com.example.eventum.data.remote.model.response.UserRemote

interface UsersRemoteRepository {
    suspend fun addFriend(senderUserId: Long, receiverUserId: Long): Boolean
    suspend fun findUsers(searchName: String): List<UserRemote>
    suspend fun createAuthorisedContact(contact: ContactRemote): Boolean
}