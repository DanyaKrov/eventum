package com.example.eventum.data.remote

import android.util.Log
import com.example.eventum.data.remote.model.request.ContactRequest
import com.example.eventum.data.remote.model.request.CustomGiftRemoteRequest
import com.example.eventum.data.remote.model.request.PresentRemoteRequest
import com.example.eventum.data.remote.model.request.WishListRemoteRequest
import com.example.eventum.screen_contacts.data.remote.dataSource.ContactsRemoteDataSource
import com.example.eventum.screen_giftList.data.remote.dataSource.GiftRemoteDataSource
import com.example.eventum.screen_mainPage.data.remote.dataSource.EventsRemoteDataSource
import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest
import com.example.eventum.screen_presents.data.remote.dataSource.PresentsRemoteDataSource
import com.example.eventum.screen_wishList.data.remote.dataSource.WishListRemoteDataSource
import com.example.eventum.screen_wishList.domain.model.WishList
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

class RetrofitClientTest {
    private lateinit var dataSource: EventsRemoteDataSource
    @Before
    fun setUp() {
        dataSource = RetrofitClient.createEventsInstance()
    }

    @Test
    fun test() = runTest{
        val response = dataSource.removeContact(2, 2)
        Log.e("testing", response.toString())
    }
}