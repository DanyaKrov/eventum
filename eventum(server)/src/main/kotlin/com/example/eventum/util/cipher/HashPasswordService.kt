package com.example.eventum.util.cipher

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class HashPasswordService {
    private val encoder = BCryptPasswordEncoder()


    fun hashPassword(password: String): String {
        return encoder.encode(password)
    }


    fun verifyPassword(rawPassword: String, hashPassword: String): Boolean {
        return encoder.matches(rawPassword, hashPassword)
    }
}