package com.example.eventum.service

import com.example.eventum.database.entity.User
import com.example.eventum.model.request.UserRequest
import com.example.eventum.model.response.UserResponse
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Service
interface UserService {
    fun getById(id: Long): User
    fun getByEmail(email: String): User
    fun getAll(): List<UserResponse>
    fun update(id: Long, user: UserRequest): UserResponse
    fun getContacts(id: Long): List<UserResponse>
    fun delete(id: Long): String
    fun create(user: UserRequest): User
    fun getByIds(ids: List<Long>): List<User>
}