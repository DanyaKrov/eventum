package com.example.eventum.screen_giftList.domain.useCase

import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_giftList.domain.model.GiftList
import com.example.eventum.screen_giftList.domain.repository.GiftListRepository
import com.example.eventum.screen_wishList.domain.model.WishList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RefreshGiftList @Inject constructor(
    private val repository: GiftListRepository
) {
    operator fun invoke(wishListId: Long, refreshLocalDatabase: Boolean = false): Flow<Resource<GiftList>> =
        flow{
            try {
                emit(Resource.Loading())
                val wishList = repository.getGiftList(refreshLocalDatabase, wishListId)
                emit(Resource.Success(wishList))
            }
            catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server"))
            }
            catch (e: Exception) {
                emit(Resource.Error("Unexpected error occurred"))
            }
        }
}