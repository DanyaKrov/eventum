package com.example.eventum.controller

import com.example.eventum.database.entity.Contact
import com.example.eventum.database.entity.Event
import com.example.eventum.database.entity.User
import com.example.eventum.model.request.EventRequest
import com.example.eventum.model.request.UserRequest
import com.example.eventum.model.response.ContactResponse
import com.example.eventum.model.response.EventResponse
import com.example.eventum.model.response.UserResponse
import com.example.eventum.service.EventService
import com.example.eventum.service.UserService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/events")
class EventController(private val eventService: EventService) {
    @GetMapping
    fun getAll(): List<EventResponse> {return eventService.getAll()}

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Event = eventService.getById(id)

    @GetMapping("/{id}/users")
    fun getUsers(@PathVariable id: Long): List<UserResponse> = eventService.getTargetUsers(id)

    @GetMapping("/{id}/contacts")
    fun getContacts(@PathVariable id: Long): List<ContactResponse> = eventService.getTargetContacts(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody event: EventRequest) = eventService.update(id, event)

    @PostMapping
    fun create(@RequestBody event: EventRequest): EventResponse = eventService.create(event)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long, response: HttpServletResponse): String = eventService.delete(id)
}