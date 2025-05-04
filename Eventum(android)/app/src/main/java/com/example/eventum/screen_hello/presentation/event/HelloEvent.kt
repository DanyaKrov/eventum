package com.example.eventum.screen_hello.presentation.event

sealed class HelloEvent {
    class MoveToLogin():
        HelloEvent()

    class MoveToSignUp():
        HelloEvent()
}