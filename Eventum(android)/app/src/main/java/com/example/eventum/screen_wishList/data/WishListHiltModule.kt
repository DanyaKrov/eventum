package com.example.eventum.screen_wishList.data

import com.example.eventum.screen_signUp.data.remote.repository.SignUpRemoteRepository
import com.example.eventum.screen_signUp.data.remote.service.SignUpRemoteService
import com.example.eventum.screen_signUp.data.service.SignUpService
import com.example.eventum.screen_signUp.domain.repository.SignUpRepository
import com.example.eventum.screen_wishList.data.local.repository.WishListLocalRepository
import com.example.eventum.screen_wishList.data.local.service.WishListLocalService
import com.example.eventum.screen_wishList.data.remote.repository.WishListRemoteRepository
import com.example.eventum.screen_wishList.data.remote.service.WishListRemoteService
import com.example.eventum.screen_wishList.data.service.WishListService
import com.example.eventum.screen_wishList.domain.repository.WishListRepository
import dagger.Binds
import javax.inject.Singleton

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