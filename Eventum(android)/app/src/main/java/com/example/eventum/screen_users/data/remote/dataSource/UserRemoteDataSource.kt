package com.example.eventum.screen_users.data.remote.dataSource

import com.example.eventum.data.remote.model.request.UserRemoteRequest
import com.example.eventum.data.remote.model.response.FriendshipResponse
import com.example.eventum.data.remote.model.response.UserRemote
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserRemoteDataSource {
    @POST("users/find/{name}")
    suspend fun findUsersByName(@Path("name") name: String): List<UserRemote>

    @POST("users/{id}/friends/{friendId}")
    suspend fun addFriend(@Path("id") id: Long, @Path("friendId") friendId: Long): UserRemote

    @POST("users/find/{name}")
    suspend fun createAuthorisedContact(@Path("name") name: String): List<UserRemote>

    @GET("users/{id}/friends/{friendId}")
    suspend fun getFriendship(@Path("id") id: Long, @Path("friendId") friendId: Long): FriendshipResponse

    @GET("users/{id}/friends")
    suspend fun getFriendsUsers(@Path("id") id: Long): List<UserRemote>
}