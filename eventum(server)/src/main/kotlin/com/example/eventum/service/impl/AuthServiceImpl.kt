package com.example.eventum.service.impl

import com.example.eventum.database.repository.UserDao
import com.example.eventum.exception.type.NotAuthorisedException
import com.example.eventum.model.request.AuthRequest
import com.example.eventum.model.response.UserResponse
import com.example.eventum.service.AuthService
import com.example.eventum.util.cipher.HashPasswordService
import com.example.eventum.util.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val dao: UserDao,
    private val hashPasswordService: HashPasswordService,
    private val mapper: UserMapper,
): AuthService {
    override fun login(authRequest: AuthRequest): UserResponse {
        val user = dao.findByEmail(authRequest.email) ?: throw NotAuthorisedException("Неверный email или пароль")
        if (!hashPasswordService.verifyPassword(authRequest.password, user.password))
            throw NotAuthorisedException("Неверный email или пароль")
        return mapper.entityToResponse(user)
    }
}