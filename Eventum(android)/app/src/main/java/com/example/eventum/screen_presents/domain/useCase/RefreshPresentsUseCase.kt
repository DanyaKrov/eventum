package com.example.eventum.screen_presents.domain.useCase

import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.repository.EventsRepository
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_presents.domain.repository.PresentsRepository
import com.example.eventum.util.mapper.EventMapper
import javax.inject.Inject

class RefreshPresentsUseCase @Inject constructor(
    private val repository: PresentsRepository
) {
    suspend operator fun invoke(wishListId: Long, refreshLocalDatabase: Boolean): List<Present> =
        repository.getPresents(wishListId, refreshLocalDatabase)
}