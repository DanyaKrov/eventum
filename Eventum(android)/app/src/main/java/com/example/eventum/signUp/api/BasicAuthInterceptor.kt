package com.example.eventum.signUp.api

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Base64

class BasicAuthInterceptor(username: String, password: String) : Interceptor {
    private val credentials: String = "Basic " +
            Base64.getEncoder().encodeToString("$username:$password".toByteArray())

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", credentials)
            .build()
        return chain.proceed(request)
    }
}