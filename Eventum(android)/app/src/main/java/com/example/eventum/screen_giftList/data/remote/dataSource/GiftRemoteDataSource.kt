package com.example.eventum.screen_giftList.data.remote.dataSource

import com.example.eventum.data.remote.model.request.CustomGiftRemoteRequest
import com.example.eventum.data.remote.model.response.GiftRemoteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GiftRemoteDataSource {
    @PUT("gifts/{giftId}")
    suspend fun updateCustom(@Path("giftId") giftId: Long,
                           @Body gift: CustomGiftRemoteRequest): GiftRemoteResponse

    @POST("contacts/{contactId}/gifts/custom")
    suspend fun createCustomGift(@Path("contactId") contactId: Long,
                                 @Body giftRequest: CustomGiftRemoteRequest): GiftRemoteResponse

    @POST("contacts/{contactId}/gifts/linked")
    suspend fun createLinkedGift(@Path("contactId") contactId: Long,
                                 @Body gift: GiftRemoteResponse): GiftRemoteResponse

    @DELETE("gifts/{id}")
    suspend fun deleteById(@Path("id") id: Long)

    @GET("contacts/{contactId}/gifts")
    suspend fun getGifts(@Path("contactId") contactId: Long): List<GiftRemoteResponse>
}