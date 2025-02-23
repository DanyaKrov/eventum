package com.example.eventum.controller

import com.example.eventum.model.request.AuthRequest
import com.example.eventum.model.response.UserResponse
import com.example.eventum.service.AuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): UserResponse = authService.login(authRequest)
}