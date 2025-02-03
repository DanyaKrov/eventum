package com.example.eventum.service.impl

import com.example.eventum.database.entity.User
import com.example.eventum.database.repository.UserDao
import com.example.eventum.model.request.UserRequest
import com.example.eventum.model.response.UserResponse
import com.example.eventum.service.UserService
import com.example.eventum.util.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val dao: UserDao,
    private val userMapper: UserMapper
): UserService {
    override fun getById(id: Long): User = dao.findById(id).orElseThrow {RuntimeException("")} // need to handle exception

    override fun getByEmail(email: String): User = dao.findByEmail(email)

    override fun getAll(): List<UserResponse> = dao.findAll().map { userMapper.entityToResponse(it) }

    override fun update(id: Long, user: UserRequest): UserResponse {
        val entity = getById(id)
        return userMapper.entityToResponse(dao.save(userMapper.updateUser(entity, user)))

    }

    override fun getContacts(id: Long): List<UserResponse> {
        val entity = getById(id)
        return entity.contacts.map { userMapper.entityToResponse(it) }
    }

    override fun delete(id: Long): String {
        val entity = getById(id)
        dao.delete(entity)
        return "Deleted with success" // handle it better
    }

    override fun create(user: UserRequest): UserResponse {
        val entity = userMapper.createUser(user)
        return userMapper.entityToResponse(dao.save(entity))
    }
}