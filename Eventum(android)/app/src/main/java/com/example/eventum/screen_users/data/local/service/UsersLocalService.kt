package com.example.eventum.screen_users.data.local.service

import com.example.eventum.data.local.dao.ContactDao
import com.example.eventum.data.local.dao.UserDao
import com.example.eventum.data.local.model.entity.ContactEntity
import com.example.eventum.data.local.model.entity.UserEntity
import com.example.eventum.data.local.model.entity.UserCrossUserRef
import com.example.eventum.screen_users.data.local.repository.UsersLocalRepository
import javax.inject.Inject

class UsersLocalService @Inject constructor(
    private val dao: UserDao,
    private val contactDao: ContactDao
): UsersLocalRepository {
    override suspend fun addFriend(senderUserId: Long, receiverUserId: Long) {
        dao.insertFriendShip(UserCrossUserRef(senderUserId, receiverUserId))
    }

    override suspend fun saveUser(user: UserEntity) = dao.insert(user)
    override suspend fun saveAuthorisedContact(contact: ContactEntity): Boolean {
        return try {
            contactDao.insert(contact)
            true
        }
        catch (_: Exception) {
            false
        }
    }

}