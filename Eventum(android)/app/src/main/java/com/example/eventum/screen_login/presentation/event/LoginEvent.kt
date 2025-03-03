package com.example.eventum.screen_login.presentation.event

sealed class LoginEvent {
    class EmailChanged(val email: String): LoginEvent()
    class PasswordChanged(val password: String): LoginEvent()
    class LoginFinished(): LoginEvent()
    class MoveToSignUp(): LoginEvent()
}