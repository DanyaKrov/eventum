package com.example.eventum.mainPage.data.remote.service

import com.example.eventum.data.api.model.EventResponse
import com.example.eventum.mainPage.data.remote.dataSource.EventsRemoteDataSource
import com.example.eventum.mainPage.domain.model.Event
import com.example.eventum.mainPage.data.remote.repository.EventsRemoteRepository
import com.example.eventum.util.mapper.EventMapper
import javax.inject.Inject

class EventsRemoteService @Inject constructor(
    private val dao: EventsRemoteDataSource,
    private val mapper: EventMapper
): EventsRemoteRepository {
    override suspend fun get(id: Long): EventResponse =
        dao.getById(id)
    override suspend fun create(event: Event): EventResponse =
        dao.create(mapper.modelToRequest(event))
    override suspend fun delete(id: Long): String = dao.deleteById(id)
    override suspend fun update(id: Long, event: Event): EventResponse =
        dao.updateById(id, mapper.modelToRequest(event))
}