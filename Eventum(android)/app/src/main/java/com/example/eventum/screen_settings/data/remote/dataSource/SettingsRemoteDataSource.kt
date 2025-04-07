package com.example.eventum.screen_settings.data.remote.dataSource

import com.example.eventum.screen_presents.domain.model.Present
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SettingsRemoteDataSource {
    @POST("users/{id}/wishList") // need to be corrected, when api is ready
    suspend fun updateUserWishListVisibility(@Path("id") id: Long, @Body visibility: Boolean)
}