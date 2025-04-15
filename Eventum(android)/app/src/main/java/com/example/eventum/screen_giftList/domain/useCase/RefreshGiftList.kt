package com.example.eventum.screen_giftList.domain.useCase

import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.repository.GiftListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RefreshGiftList @Inject constructor(
    private val repository: GiftListRepository
) {
    operator fun invoke(contactRemoteId: Long, refreshLocalDatabase: Boolean = false): Flow<Resource<List<Gift>>> =
        flow{
            try {
                emit(Resource.Loading())
                val gifts = repository.getGifts(contactRemoteId, refreshLocalDatabase)
                emit(Resource.Success(gifts))
            }
            catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server"))
            }
            catch (e: Exception) {
                emit(Resource.Error("Unexpected error occurred"))
            }
        }
}