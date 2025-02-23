package com.example.eventum.service

import com.example.eventum.database.entity.Contact
import com.example.eventum.database.entity.Event
import com.example.eventum.database.entity.User
import com.example.eventum.model.request.EventRequest
import com.example.eventum.model.request.UserRequest
import com.example.eventum.model.response.ContactResponse
import com.example.eventum.model.response.EventResponse
import com.example.eventum.model.response.UserResponse
import org.springframework.stereotype.Service

@Service
interface EventService {
    fun getById(id: Long): Event
    fun getAll(): List<EventResponse>
    fun update(id: Long, event: EventRequest): EventResponse
    fun getTargetContacts(id: Long): List<ContactResponse>
    fun delete(id: Long): String
    fun create(event: EventRequest): EventResponse
}