package com.example.eventum.screen_wishList.data.service

import android.util.Log
import com.example.eventum.data.local.repository.UserLocalRepository
import com.example.eventum.data.remote.model.request.WishListRemoteRequest
import com.example.eventum.screen_presents.data.local.repository.PresentsLocalRepository
import com.example.eventum.screen_wishList.data.local.repository.WishListLocalRepository
import com.example.eventum.screen_wishList.data.remote.repository.WishListRemoteRepository
import com.example.eventum.screen_wishList.domain.model.WishList
import com.example.eventum.screen_wishList.domain.repository.WishListRepository
import com.example.eventum.util.mapper.PresentMapper
import com.example.eventum.util.mapper.WishListMapper
import javax.inject.Inject

class WishListService @Inject constructor(
    private val localRepository: WishListLocalRepository,
    private val localPresentsRepository: PresentsLocalRepository,
    private val remoteRepository: WishListRemoteRepository,
    private val userLocalRepository: UserLocalRepository,
    private val wishListMapper: WishListMapper,
    private val presentMapper: PresentMapper
): WishListRepository {
    override suspend fun getWishList(userId: Long, forceRefresh: Boolean): WishList {
        if (forceRefresh) {
            try {
                val remoteWishList = remoteRepository.getWishList(userId)
                localRepository.deleteWishList(userId)
                val entity = wishListMapper.fromResponseToEntity(remoteWishList, userId)
                localRepository.createWishList(entity, remoteWishList.presents.map {
                    presentMapper.fromRemoteToEntity(it, remoteWishList.id)
                })
            } catch (_: Exception) {
            }
        } else {
            val localWishList = localRepository.getWishList(userId)
            localWishList.presents.ifEmpty {
                val remoteWishList = remoteRepository.getWishList(userId)
                val remotePresents = remoteWishList.presents
                remotePresents.forEach { localPresentsRepository.insert(
                    presentMapper.fromRemoteToEntity(it, remoteWishList.id )) }
            }
        }
        val wishList = localRepository.getWishList(userId)
        return wishListMapper.fromEntityToModel(wishList = wishList.wishList,
            presents = wishList.presents.map { presentMapper.fromEntityToModel(it) })
    }

    override suspend fun changeVisibility(userId: Long, visibility: Boolean) {
        remoteRepository.changeVisibility(userId, WishListRemoteRequest(visibility))
    }
}