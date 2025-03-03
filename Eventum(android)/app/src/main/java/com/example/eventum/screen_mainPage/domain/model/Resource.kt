package com.example.eventum.screen_mainPage.domain.model

sealed class Resource<T>(val value: T? = null, val message: String? = null) {
    class Loaded<T>(value: T): Resource<T>(value)
    class Loading<T>(): Resource<T>()
    class ErrorOccurred<T>(message: String?): Resource<T>(message=message)
}