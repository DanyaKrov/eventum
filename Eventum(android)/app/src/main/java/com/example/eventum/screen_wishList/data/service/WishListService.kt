package com.example.eventum.screen_wishList.data.service

import com.example.eventum.data.local.repository.UserLocalRepository
import com.example.eventum.screen_wishList.data.local.repository.WishListLocalRepository
import com.example.eventum.screen_wishList.data.remote.repository.WishListRemoteRepository
import com.example.eventum.screen_wishList.domain.model.WishList
import com.example.eventum.screen_wishList.domain.repository.WishListRepository
import com.example.eventum.util.mapper.PresentMapper
import com.example.eventum.util.mapper.WishListMapper
import javax.inject.Inject

class WishListService @Inject constructor(
    private val localRepository: WishListLocalRepository,
    private val remoteRepository: WishListRemoteRepository,
    private val userLocalRepository: UserLocalRepository,
    private val wishListMapper: WishListMapper,
    private val presentMapper: PresentMapper
): WishListRepository {
    override suspend fun getWishList(wishListId: Long, forceRefresh: Boolean): WishList {
        return if (forceRefresh) {
            try {
                refreshLocalWishList(wishListId)
            } catch (e: Exception) {
                getLocalWishList(wishListId)
            }
        } else {
            try {
                getLocalWishList(wishListId)
            }
            catch (e: Exception) {
                refreshLocalWishList(wishListId)
            }
        }
    }

    private suspend fun getLocalWishList(wishListId: Long): WishList {
        val localWishList = localRepository.getWishList(wishListId)
        return wishListMapper.fromEntityToModel(localWishList.wishList,
            localWishList.presents.map { presentMapper.fromEntityToModel(it) })
    }

    private suspend fun refreshLocalWishList(userRemoteId: Long): WishList  {
        val remoteWishList = remoteRepository.getWishList(userRemoteId)
        localRepository.deleteWishList(userRemoteId)
        localRepository.createWishList(wishListMapper.fromResponseToEntity(remoteWishList, userRemoteId),
            remoteWishList.presents.map { presentMapper.fromRemoteToEntity(it) })
        val presents = remoteWishList.presents.map { presentMapper.fromRemoteToModel(it) }
        return wishListMapper.fromResponseToModel(remoteWishList, presents, userRemoteId)
    }

    override suspend fun updateWishList(newAvailability: Boolean, wishList: WishList): Boolean {
        return try {
            // need to add update of presents in remote database
            val presents = wishList.presents.map { presentMapper.fromModelToRemoteRequest(it) }
            remoteRepository.updateWishList(wishList.userId,  wishListMapper.fromModelToRemoteRequest(newAvailability))
            localRepository.updateWishList(wishListMapper.fromModelToEntity(wishList),
                wishList.presents.map { presentMapper.fromModelToEntity(it) })
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun changeVisibility(visibilityCode: String): Boolean {
        TODO("Not yet implemented")
    }
}