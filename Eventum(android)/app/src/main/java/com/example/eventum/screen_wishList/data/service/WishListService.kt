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

    private suspend fun refreshLocalWishList(wishListId: Long): WishList  {
        val remoteWishList = remoteRepository.getWishList(wishListId)
        localRepository.deleteWishList(wishListId)
        localRepository.createWishList(wishListMapper.fromResponseToEntity(remoteWishList),
            remoteWishList.presents.map { presentMapper.fromModelToEntity(it) })
        return wishListMapper.fromResponseToModel(remoteWishList)
    }

    override suspend fun updateWishList(wishList: WishList): Boolean {
        return try {
            // need to add update of presents in remote database
            remoteRepository.updateWishList(wishListMapper.fromModelToResponse(wishList))
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

    override suspend fun deleteWishList(wishListRemoteId: Long): Boolean {
        return try {
            remoteRepository.deleteWishList(wishListRemoteId)
            localRepository.deleteWishList(wishListRemoteId)
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun createWishList(wishList: WishList): Boolean {
        return try {
            val remoteId = remoteRepository.createWishList(wishListMapper.fromModelToResponse(wishList))
            wishList.remoteId = remoteId
            localRepository.createWishList(wishListMapper.fromModelToEntity(wishList),
                wishList.presents.map { presentMapper.fromModelToEntity(it) })
        }
        catch (e: Exception) {
            false
        }
    }
}