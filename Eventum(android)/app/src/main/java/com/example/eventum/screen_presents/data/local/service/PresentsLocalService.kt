package com.example.eventum.screen_presents.data.local.service

import com.example.eventum.data.roomDatabase.dao.PresentDao
import com.example.eventum.screen_presents.data.local.repository.PresentsLocalRepository
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.util.mapper.PresentMapper
import javax.inject.Inject

class PresentsLocalService @Inject constructor(
    private val dao: PresentDao,
    private val mapper: PresentMapper
): PresentsLocalRepository {
    override suspend fun insert(present: Present) {
        TODO("Not yet implemented")
    }

    override suspend fun getPresents(wishListId: Long): List<Present> = dao.getAll(wishListId).map {
        mapper.fromEntityToModel(it)
    }

    override suspend fun updatePresent(newPresent: Present): String {
        return try {
            val oldPresent = dao.get(newPresent.id)
            dao.update(mapper.updateEntity(oldPresent, newPresent))
            "Updated with success"
        }
        catch (e: Exception) {
            "Couldn't update this present"
        }
    }

    override suspend fun deletePresent(id: Long): String {
        return try {
            dao.delete(id)
            "Deleted with success"
        }
        catch (e: Exception) {
            "Couldn't delete this present"
        }
    }

    override suspend fun deleteAll(wishListId: Long): String {
        return try {
            dao.deleteAll(wishListId)
            "Deleted with success"
        }
        catch (e: Exception) {
            "Couldn't delete this present"
        }
    }
}