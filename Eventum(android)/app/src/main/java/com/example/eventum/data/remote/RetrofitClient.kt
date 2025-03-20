package com.example.eventum.data.remote

import com.example.eventum.common.Constants
import com.example.eventum.screen_contacts.data.remote.dataSource.ContactsRemoteDataSource
import com.example.eventum.screen_login.data.remote.api.LoginApiService
import com.example.eventum.screen_mainPage.data.remote.dataSource.EventsRemoteDataSource
import com.example.eventum.screen_presents.data.remote.dataSource.PresentsRemoteDataSource
import com.example.eventum.screen_presents.data.remote.dataSource.WishListRemoteDataSource
import com.example.eventum.screen_signUp.data.remote.dataSource.UsersRemoteDataSource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val client = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor("root", "pass"))
        .build()

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun createUsersInstance(): UsersRemoteDataSource {
        return instance.create(UsersRemoteDataSource::class.java)
    }

    fun createLoginInstance(): LoginApiService {
        return instance.create(LoginApiService::class.java)
    }

    fun createEventsInstance(): EventsRemoteDataSource {
        return instance.create(EventsRemoteDataSource::class.java)
    }

    fun createPresentsInstance(): PresentsRemoteDataSource {
        return instance.create(PresentsRemoteDataSource::class.java)
    }

    fun createWishListRemoteDataSource(): WishListRemoteDataSource {
        return instance.create(WishListRemoteDataSource::class.java)
    }

    fun createContactsRemoteDataSource(): ContactsRemoteDataSource {
        return instance.create(ContactsRemoteDataSource::class.java)
    }
}