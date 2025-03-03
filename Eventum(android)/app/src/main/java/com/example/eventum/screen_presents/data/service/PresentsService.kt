package com.example.eventum.screen_presents.data.service

import com.example.eventum.data.roomDatabase.dao.PresentDao
import com.example.eventum.screen_presents.data.local.repository.PresentsLocalRepository
import com.example.eventum.screen_presents.data.remote.repository.PresentsRemoteRepository
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_presents.domain.repository.PresentsRepository
import javax.inject.Inject

class PresentsService @Inject constructor(
    private val localRepository: PresentsLocalRepository,
    private val remoteRepository: PresentsRemoteRepository
): PresentsRepository {
    override suspend fun getPresents(wishListId: Long, forceRefresh: Boolean): List<Present> {
        return if (forceRefresh) {
            try {
                val remoteEvents = remoteRepository.getAll(wishListId)
                localRepository.deleteAll(wishListId)
                remoteEvents.forEach {localRepository.insert(it)}
                remoteEvents
            } catch (e: Exception) {
                localRepository.getPresents(wishListId)
            }
        } else {
            val localEvents = localRepository.getPresents(wishListId)
            if (localEvents.isNotEmpty()) {
                localEvents
            } else {
                val remoteEvents = remoteRepository.getAll(wishListId)
                remoteEvents.forEach {localRepository.insert(it)}
                remoteEvents
            }
        }
    }

    override suspend fun deletePresent(present: Present): String {
        remoteRepository.delete(present.id)
        return localRepository.deletePresent(present.id)
    }

    override suspend fun editPresent(present: Present): String {
        remoteRepository.update(present.id, present)
        return localRepository.updatePresent(present)
    }

    override suspend fun createPresent(present: Present) {
        val presentWithId = remoteRepository.insert(present)
        localRepository.insert(presentWithId)
    }
}