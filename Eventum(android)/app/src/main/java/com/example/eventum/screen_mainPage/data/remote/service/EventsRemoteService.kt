package com.example.eventum.screen_mainPage.data.remote.service

import com.example.eventum.data.remote.model.response.EventRemote
import com.example.eventum.screen_mainPage.data.remote.dataSource.EventsRemoteDataSource
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.data.remote.repository.EventsRemoteRepository
import com.example.eventum.util.mapper.EventMapper
import javax.inject.Inject

class EventsRemoteService @Inject constructor(
    private val dao: EventsRemoteDataSource,
    private val mapper: EventMapper
): EventsRemoteRepository {
    override suspend fun get(id: Long): EventRemote =
        dao.getById(id)
    override suspend fun create(event: Event): EventRemote =
        dao.create(mapper.modelToRequest(event))
    override suspend fun delete(id: Long): String = dao.deleteById(id)
    override suspend fun update(id: Long, event: Event): EventRemote =
        dao.updateById(id, mapper.modelToRequest(event))
}