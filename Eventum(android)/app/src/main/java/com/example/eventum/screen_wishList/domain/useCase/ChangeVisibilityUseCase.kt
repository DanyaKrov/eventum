package com.example.eventum.screen_wishList.domain.useCase

import com.example.eventum.domain.model.Operation
import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_wishList.domain.model.WishList
import com.example.eventum.screen_wishList.domain.repository.WishListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ChangeVisibilityUseCase @Inject constructor(
    private val repository: WishListRepository
) {
    operator fun invoke(userId: Long, newVisibility: Boolean): Flow<Operation> =
        flow{
            try {
                emit(Operation.Loading())
                repository.changeVisibility(userId, newVisibility)
                emit(Operation.Success())
            }
            catch (e: IOException) {
                emit(Operation.Error("Couldn't reach server"))
            }
            catch (e: Exception) {
                emit(Operation.Error("Unexpected error occurred"))
            }
        }
}