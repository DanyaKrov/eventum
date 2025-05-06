package com.example.eventum.screen_mainPage.data.remote.dataSource

import com.example.eventum.data.remote.model.response.ContactRemote
import com.example.eventum.data.remote.model.response.EventRemote
import com.example.eventum.screen_mainPage.data.remote.entity.EventRequest
import retrofit2.http.DELETE
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EventsRemoteDataSource {
    @GET("users/{userId}/events")
    suspend fun getUserEvents(@Path("userId") userId: Long): List<EventRemote>

    @GET("events/{id}")
    suspend fun getEvent(@Path("id") id: Long): EventRemote

    @PUT("events/{id}")
    suspend fun updateById(@Path("id") id: Long, @Body event: EventRequest): EventRemote

    @POST("users/{userId}/events")
    suspend fun create(@Path("userId") userId: Long, @Body event: EventRequest): EventRemote

    @DELETE("events/{id}")
    suspend fun deleteById(@Path("id") id: Long)

    @GET("events/{id}/contacts")
    suspend fun getContacts(@Path("id") id: Long): List<ContactRemote>


    @PUT("events/{id}/contacts/{contactId}")
    suspend fun addContact(@Path("id") id: Long, @Path("contactId") contactId: Long): EventRemote

    @DELETE("events/{id}/contacts/{contactId}")
    suspend fun removeContact(@Path("id") id: Long, @Path("contactId") contactId: Long): EventRemote
}