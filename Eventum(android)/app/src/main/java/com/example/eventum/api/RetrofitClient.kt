package com.example.eventum.api

import com.example.eventum.login.api.LoginRepository
import com.example.eventum.signUp.api.SignUpRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8000/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor("root", "pass"))
        .build()

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun createSignUpInstance(): SignUpRepository {
        return instance.create(SignUpRepository::class.java)
    }

    fun createLoginInstance(): LoginRepository {
        return instance.create(LoginRepository::class.java)
    }
}