package com.example.eventum.data.api

import com.example.eventum.common.Constants
import com.example.eventum.login.data.remote.api.LoginApiService
import com.example.eventum.mainPage.data.remote.dataSource.EventsRemoteDataSource
import com.example.eventum.signUp.data.api.SignUpRepository
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

    fun createSignUpInstance(): SignUpRepository {
        return instance.create(SignUpRepository::class.java)
    }

    fun createLoginInstance(): LoginApiService {
        return instance.create(LoginApiService::class.java)
    }

    fun createEventsInstance(): EventsRemoteDataSource {
        return instance.create(EventsRemoteDataSource::class.java)
    }
}