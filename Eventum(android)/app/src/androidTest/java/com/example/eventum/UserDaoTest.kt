package com.example.eventum

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.eventum.data.local.app.AppDatabase
import com.example.eventum.data.local.dao.ContactDao
import com.example.eventum.data.local.dao.UserDao
import com.example.eventum.data.local.model.entity.UserEntity
import com.example.eventum.data.local.model.entity.UserCrossUserRef
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var dao: UserDao
    private lateinit var contactDao: ContactDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.userDao()
        contactDao = db.contactDao()
    }

    @Test
    fun insert() = runTest{
        val user1 = UserEntity(remoteId = 0, name = "Kolchak", email = "kolchak@mail.ru", picture = "")
        dao.insert(user1)
        val user2 = UserEntity(remoteId = 1, name = "Semenov", email = "kolchak@mail.ru", picture = "")
        dao.insert(user2)
        val relation = UserCrossUserRef(userId = user1.remoteId, friendUserId = user2.remoteId)

        dao.insertFriendShip(relation)
        println(dao.getFriends(0).friends.toString())
    }


    @After
    fun tearDown() {
        db.close()
    }

}