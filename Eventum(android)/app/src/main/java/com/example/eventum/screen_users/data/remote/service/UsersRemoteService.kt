package com.example.eventum.screen_users.data.remote.service

import com.example.eventum.data.remote.model.response.ContactRemote
import com.example.eventum.data.remote.model.response.UserRemote
import com.example.eventum.screen_users.data.remote.dataSource.UserRemoteDataSource
import com.example.eventum.screen_users.data.remote.repository.UsersRemoteRepository
import javax.inject.Inject

class UsersRemoteService @Inject constructor(
    private val dataSource: UserRemoteDataSource
): UsersRemoteRepository {
    override suspend fun addFriend(senderUserId: Long, receiverUserId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun findUsers(searchName: String): List<UserRemote> {
        TODO("Not yet implemented")
    }

    override suspend fun createAuthorisedContact(contact: ContactRemote): Boolean {
        TODO("Not yet implemented")
    }
}