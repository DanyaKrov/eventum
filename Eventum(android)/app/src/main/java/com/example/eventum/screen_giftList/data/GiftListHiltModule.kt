package com.example.eventum.screen_giftList.data

import com.example.eventum.screen_giftList.data.local.repository.GiftListLocalRepository
import com.example.eventum.screen_giftList.data.local.service.GiftListLocalService
import com.example.eventum.screen_giftList.data.remote.repository.GiftListRemoteRepository
import com.example.eventum.screen_giftList.data.remote.service.GiftListRemoteService
import com.example.eventum.screen_giftList.data.service.GiftListService
import com.example.eventum.screen_giftList.domain.repository.GiftListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GiftListHiltModule {
    @Binds
    @Singleton
    abstract fun bindGiftListLocalRepository(
        impl: GiftListLocalService
    ): GiftListLocalRepository


    @Binds
    @Singleton
    abstract fun bindGiftListRemoteRepository(
        impl: GiftListRemoteService
    ): GiftListRemoteRepository

    @Binds
    @Singleton
    abstract fun bindGiftListRepository(
        impl: GiftListService
    ): GiftListRepository
}