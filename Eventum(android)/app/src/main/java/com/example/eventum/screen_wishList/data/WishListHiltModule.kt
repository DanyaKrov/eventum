package com.example.eventum.screen_wishList.data

import com.example.eventum.screen_wishList.data.local.repository.WishListLocalRepository
import com.example.eventum.screen_wishList.data.local.service.WishListLocalService
import com.example.eventum.screen_wishList.data.remote.repository.WishListRemoteRepository
import com.example.eventum.screen_wishList.data.remote.service.WishListRemoteService
import com.example.eventum.screen_wishList.data.service.WishListService
import com.example.eventum.screen_wishList.domain.repository.WishListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WishListHiltModule{
    @Binds
    @Singleton
    abstract fun bindWishListLocalRepository(
        impl: WishListLocalService
    ): WishListLocalRepository

    @Binds
    @Singleton
    abstract fun bindWishListRemoteRepository(
        impl: WishListRemoteService
    ): WishListRemoteRepository

    @Binds
    @Singleton
    abstract fun bindWishListRepository(
        impl: WishListService
    ): WishListRepository
}