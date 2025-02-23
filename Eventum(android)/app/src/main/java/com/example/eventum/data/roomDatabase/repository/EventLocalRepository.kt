package com.example.eventum.data.roomDatabase.repository

import com.example.eventum.data.roomDatabase.dao.EventDao
import com.example.eventum.data.roomDatabase.entity.EventEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventLocalRepository @Inject constructor(private val eventDao: EventDao) {
    suspend fun insertEvent(event: EventEntity) = eventDao.insert(event)
    suspend fun getUser(id: Long): EventEntity = eventDao.get(id)
}