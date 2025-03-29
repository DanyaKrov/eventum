package com.example.eventum.screen_wishList.domain.useCase

import com.example.eventum.screen_wishList.domain.model.WishList
import com.example.eventum.screen_wishList.domain.repository.WishListRepository
import javax.inject.Inject

class CreateWishListUseCase @Inject constructor(
    private val repository: WishListRepository
) {
    suspend operator fun invoke(wishList: WishList): Boolean = repository.createWishList(wishList)
}