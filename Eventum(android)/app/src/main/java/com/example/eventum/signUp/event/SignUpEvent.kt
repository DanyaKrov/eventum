package com.example.eventum.signUp.event

sealed class SignUpEvent {
    class EmailChanged(val emailAddress: String):
        SignUpEvent()

    class PasswordChanged(val password: String):
        SignUpEvent()

    class ButtonClicked():
        SignUpEvent()
}