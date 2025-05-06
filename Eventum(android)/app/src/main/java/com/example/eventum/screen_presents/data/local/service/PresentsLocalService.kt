package com.example.eventum.screen_presents.data.local.service

import android.util.Log
import com.example.eventum.data.local.dao.PresentDao
import com.example.eventum.data.local.model.entity.PresentEntity
import com.example.eventum.screen_presents.data.local.repository.PresentsLocalRepository
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.util.mapper.PresentMapper
import javax.inject.Inject

class PresentsLocalService @Inject constructor(
    private val dao: PresentDao
): PresentsLocalRepository {
    override suspend fun insert(present: PresentEntity) = dao.insert(present)

    override suspend fun getPresents(wishListId: Long): List<PresentEntity> = dao.getAll(wishListId)

    override suspend fun getPresent(presentRemoteId: Long): PresentEntity = dao.get(presentRemoteId)

    override suspend fun updatePresent(newPresent: PresentEntity): Boolean {
        Log.i("testing", newPresent.toString())
        return try {
            dao.update(newPresent)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override suspend fun deletePresent(remoteId: Long): String {
        return try {
            dao.delete(remoteId)
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