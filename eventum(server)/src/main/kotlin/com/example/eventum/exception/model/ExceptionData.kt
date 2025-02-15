package com.example.eventum.exception.model

import java.time.LocalDateTime

data class ExceptionData(
    val message: String,
    val status: Int,
    val timestamp: LocalDateTime = LocalDateTime.now()
)