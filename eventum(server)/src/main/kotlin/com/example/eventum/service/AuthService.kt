package com.example.eventum.service

import com.example.eventum.model.request.AuthRequest
import org.springframework.stereotype.Service

@Service
interface AuthService {
    fun login(authRequest: AuthRequest): Boolean
}