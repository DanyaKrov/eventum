package com.example.eventum.data.remote

import com.example.eventum.common.Constants
import com.example.eventum.screen_contacts.data.remote.dataSource.ContactsRemoteDataSource
import com.example.eventum.screen_giftList.data.remote.dataSource.GiftListRemoteDataSource
import com.example.eventum.screen_giftList.data.remote.dataSource.GiftRemoteDataSource
import com.example.eventum.screen_login.data.remote.dataSource.LoginRemoteDataSource
import com.example.eventum.screen_mainPage.data.remote.dataSource.EventsRemoteDataSource
import com.example.eventum.screen_presents.data.remote.dataSource.PresentsRemoteDataSource
import com.example.eventum.screen_profile.data.remote.dataSource.ProfileRemoteDataSource
import com.example.eventum.screen_settings.data.remote.dataSource.SettingsRemoteDataSource
import com.example.eventum.screen_signUp.data.remote.dataSource.UsersRemoteDataSource
import com.example.eventum.screen_wishList.data.remote.dataSource.WishListRemoteDataSource
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

    fun createLoginInstance(): LoginRemoteDataSource {
        return instance.create(LoginRemoteDataSource::class.java)
    }

    fun createEventsInstance(): EventsRemoteDataSource {
        return instance.create(EventsRemoteDataSource::class.java)
    }

    fun createPresentsInstance(): PresentsRemoteDataSource {
        return instance.create(PresentsRemoteDataSource::class.java)
    }

    fun createGiftListInstance(): GiftListRemoteDataSource {
        return instance.create(GiftListRemoteDataSource::class.java)
    }

    fun createGiftInstance(): GiftRemoteDataSource {
        return instance.create(GiftRemoteDataSource::class.java)
    }

    fun createProfileInstance(): ProfileRemoteDataSource {
        return instance.create(ProfileRemoteDataSource::class.java)
    }

    fun createWishListRemoteDataSource(): WishListRemoteDataSource {
        return instance.create(WishListRemoteDataSource::class.java)
    }

    fun createContactsRemoteDataSource(): ContactsRemoteDataSource {
        return instance.create(ContactsRemoteDataSource::class.java)
    }

    fun createSettingsRemoteDataSource(): SettingsRemoteDataSource {
        return instance.create(SettingsRemoteDataSource::class.java)
    }
}