package com.example.eventum.screen_presents.data.service

import com.example.eventum.screen_presents.data.local.repository.PresentsLocalRepository
import com.example.eventum.screen_presents.data.remote.repository.PresentsRemoteRepository
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_presents.domain.repository.PresentsRepository
import com.example.eventum.util.mapper.PresentMapper
import javax.inject.Inject

class PresentsService @Inject constructor(
    private val localRepository: PresentsLocalRepository,
    private val remoteRepository: PresentsRemoteRepository,
    private val mapper: PresentMapper
): PresentsRepository {
    override suspend fun getPresents(wishListId: Long, forceRefresh: Boolean): List<Present> {
        return if (forceRefresh) {
            try {
                val remoteEvents = remoteRepository.getAll(wishListId)
                localRepository.deleteAll(wishListId)
                remoteEvents.forEach {localRepository.insert(mapper.fromRemoteToEntity(it))}
                remoteEvents.map { mapper.fromRemoteToModel(it) }
            } catch (e: Exception) {
                localRepository.getPresents(wishListId).map { mapper.fromEntityToModel(it) }
            }
        } else {
            val localEvents = localRepository.getPresents(wishListId)
            if (localEvents.isNotEmpty()) {
                localEvents.map { mapper.fromEntityToModel(it) }
            } else {
                val remoteEvents = remoteRepository.getAll(wishListId)
                remoteEvents.forEach {localRepository.insert(mapper.fromRemoteToEntity(it))}
                remoteEvents.map { mapper.fromRemoteToModel(it) }
            }
        }
    }

    override suspend fun deletePresent(present: Present): String {
        remoteRepository.delete(present.id)
        return localRepository.deletePresent(present.id)
    }

    override suspend fun editPresent(present: Present): String {
        remoteRepository.update(present.id, mapper.fromModelToRemoteRequest(present))
        return localRepository.updatePresent(mapper.fromModelToEntity(present))
    }

    override suspend fun createPresent(present: Present) {
        // add remote present
        localRepository.insert(mapper.fromModelToEntity(present))
    }

    override suspend fun getPresent(remoteId: Long): Present {
        return try {
           mapper.fromEntityToModel(localRepository.getPresent(remoteId))
        }
        catch (_: Exception) {
            mapper.fromRemoteToModel(remoteRepository.get(remoteId))
        }
    }
}