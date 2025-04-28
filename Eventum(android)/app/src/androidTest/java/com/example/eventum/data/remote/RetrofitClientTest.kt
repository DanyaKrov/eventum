package com.example.eventum.data.remote

import android.util.Log
import com.example.eventum.data.remote.model.request.UserRemoteRequest
import com.example.eventum.screen_mainPage.data.remote.dataSource.EventsRemoteDataSource
import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest
import com.example.eventum.screen_signUp.data.remote.dataSource.SignUpRemoteDataSource
import com.example.eventum.screen_users.data.remote.dataSource.UserRemoteDataSource
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

class RetrofitClientTest {
    private lateinit var dataSource: UserRemoteDataSource
    @Before
    fun setUp() {
        dataSource = RetrofitClient.createUserRemoteDataSource()
    }

    @Test
    fun test() = runTest{
        val response = dataSource.getFriendship(
            2, 1
        )
        Log.e("testing", response.toString())
    }
}