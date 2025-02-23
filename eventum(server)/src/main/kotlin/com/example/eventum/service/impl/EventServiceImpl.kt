package com.example.eventum.service.impl

import com.example.eventum.database.entity.Contact
import com.example.eventum.database.entity.Event
import com.example.eventum.database.repository.EventDao
import com.example.eventum.database.repository.UserDao
import com.example.eventum.exception.type.NotFoundException
import com.example.eventum.model.request.EventRequest
import com.example.eventum.model.response.ContactResponse
import com.example.eventum.model.response.EventResponse
import com.example.eventum.model.response.UserResponse
import com.example.eventum.service.EventService
import com.example.eventum.service.UserService
import com.example.eventum.util.mapper.ContactMapper
import com.example.eventum.util.mapper.EventMapper
import com.example.eventum.util.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class EventServiceImpl(
    private val dao: EventDao,
    private val mapper: EventMapper,
    private val userMapper: UserMapper,
    private val contactMapper: ContactMapper
)
    : EventService {
    override fun getById(id: Long): Event = dao.findById(id).orElseThrow {NotFoundException()}

    override fun getAll(): List<EventResponse> = dao.findAll().map {
        mapper.entityToResponse(it)
    }

    override fun update(id: Long, event: EventRequest): EventResponse {
        val entity = getById(id)
        return mapper.entityToResponse(dao.save(mapper.updateEvent(entity, event)))
    }

    override fun getTargetContacts(id: Long): List<ContactResponse> = getById(id).contactsIds.map {
        contactMapper.entityToResponse(it)
    }

    override fun delete(id: Long): String {
        try {
            dao.deleteById(id)
            return "deleted success"
        }
        catch (e: Exception) {
            throw NotFoundException()
        }
    }

    override fun create(event: EventRequest): EventResponse {
        val entity = mapper.createEvent(event)
        return mapper.entityToResponse(dao.save(entity))
    }
}