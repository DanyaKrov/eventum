package com.example.eventum.screen_users.data.service

import com.example.eventum.screen_users.data.local.repository.UsersLocalRepository
import com.example.eventum.screen_users.domain.model.UserModel
import com.example.eventum.screen_users.domain.repository.UsersRepository

class UsersService(
    private val localRepository: UsersLocalRepository,

): UsersRepository {
    override suspend fun getUsers(searchName: String): List<UserModel> {
        TODO("Not yet implemented")
    }

    override suspend fun addFriend(senderId: Long, receiverId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun addAuthorisedContact(selectedUser: UserModel): Boolean {
        TODO("Not yet implemented")
    }
}