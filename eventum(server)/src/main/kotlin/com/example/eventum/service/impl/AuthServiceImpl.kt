package com.example.eventum.service.impl

import com.example.eventum.database.repository.UserDao
import com.example.eventum.model.request.AuthRequest
import com.example.eventum.service.AuthService
import com.example.eventum.util.cipher.HashPasswordService
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val dao: UserDao,
    private val hashPasswordService: HashPasswordService
): AuthService {
    override fun login(authRequest: AuthRequest): Boolean {
        try {
            val user = dao.findByEmail(authRequest.email)
            return (hashPasswordService.verifyPassword(authRequest.password, user.password))
        }
        catch(ex: Exception) {
            return false
        }
    }
}