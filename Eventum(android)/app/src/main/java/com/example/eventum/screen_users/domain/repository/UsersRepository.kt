package com.example.eventum.screen_users.domain.repository

import com.example.eventum.screen_users.domain.model.UserModel


interface UsersRepository {
    suspend fun getUsers(searchName: String): List<UserModel>
    suspend fun addFriend(senderId: Long, receiverId: Long)
    suspend fun addAuthorisedContact(selectedUser: UserModel): Boolean
}