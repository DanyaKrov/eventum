package com.example.eventum.screen_contacts.data.remote.dataSource

import com.example.eventum.data.remote.model.request.ContactRequest
import com.example.eventum.data.remote.model.response.ContactRemote
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
    suspend fun updateById(@Path("id") id: Long, @Body contact: ContactRequest): ContactRemote

    @POST("users/{id}/contacts")
    suspend fun create(@Path("id") userId: Long, @Body contact: ContactRequest): ContactRemote

    @GET("users/{id}/contacts")
    suspend fun getUserContacts(@Path("id") userId: Long): List<ContactRemote>

    @DELETE("contacts/{id}")
    suspend fun deleteById(@Path("id") id: Long): String
}