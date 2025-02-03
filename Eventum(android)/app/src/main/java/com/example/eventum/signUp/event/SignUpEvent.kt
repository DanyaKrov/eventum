package com.example.eventum.signUp.event

sealed class SignUpEvent {
    class EmailChanged(val emailAddress: String):
        SignUpEvent()

    class PasswordChanged(val password: String):
        SignUpEvent()

    class SignUpFinished():
        SignUpEvent()

    class MoveToLogin():
        SignUpEvent()

    class SecondPasswordChanged(val password: String):
        SignUpEvent()
}