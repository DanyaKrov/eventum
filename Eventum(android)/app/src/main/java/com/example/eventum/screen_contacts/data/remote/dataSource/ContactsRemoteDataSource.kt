package com.example.eventum.screen_contacts.data.remote.dataSource

import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_presents.domain.model.Present
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ContactsRemoteDataSource {
    @PUT("contacts/{id}")
    suspend fun updateById(@Path("id") id: Long, @Body contact: Contact): Contact

    @POST("contacts")
    suspend fun create(@Body contact: Contact): Contact

    @GET("contacts")
    suspend fun getAll(): List<Contact>

    @DELETE("contacts/{id}")
    suspend fun deleteById(@Path("id") id: Long): String
}