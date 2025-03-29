package com.example.eventum.screen_wishList.domain.useCase

import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_wishList.domain.model.WishList
import com.example.eventum.screen_wishList.domain.repository.WishListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RefreshWishListUseCase @Inject constructor(
    private val repository: WishListRepository
) {
    operator fun invoke(wishListId: Long, refreshLocalDatabase: Boolean = false): Flow<Resource<WishList>> =
        flow{
            try {
                emit(Resource.Loading())
                val wishList = repository.getWishList(wishListId, refreshLocalDatabase)
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