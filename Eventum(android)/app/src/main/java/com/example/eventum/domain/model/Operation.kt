package com.example.eventum.domain.model

sealed class Operation(val isFinished: Boolean, val message: String? = null) {
    class Success(): Operation(true)
    class Error(message: String): Operation(false, message)
    class Loading(): Operation(false)
}