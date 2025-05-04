package com.example.eventum.screen_signUp.presentation.event

import com.example.eventum.screen_signUp.domain.model.SignUpModel

sealed class SignUpEvent {
    class NameChanged(val name: String):
        SignUpEvent()

    class EmailChanged(val emailAddress: String):
        SignUpEvent()

    class PasswordChanged(val password: String):
        SignUpEvent()

    class SignUpFinished(val signUpModel: SignUpModel):
        SignUpEvent()

    class MoveToLogin():
        SignUpEvent()

    class SecondPasswordChanged(val password: String):
        SignUpEvent()
}