package com.example.eventum.screen_giftList.domain.useCase

import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.repository.GiftListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateGift @Inject constructor(
    private val repository: GiftListRepository
) {
    operator fun invoke(changedGift: Gift): Flow<Resource<Boolean>> =
        flow{
            try {
                emit(Resource.Loading())
                val result = repository.updateCustomGift(changedGift)
                emit(Resource.Success(result))
            }
            catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server"))
            }
            catch (e: Exception) {
                emit(Resource.Error("Unexpected error occurred"))
            }
        }
}