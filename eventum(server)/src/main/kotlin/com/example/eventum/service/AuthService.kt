package com.example.eventum.service

import com.example.eventum.model.request.AuthRequest
import com.example.eventum.model.response.UserResponse
import org.springframework.stereotype.Service

@Service
interface AuthService {
    fun login(authRequest: AuthRequest): UserResponse
}