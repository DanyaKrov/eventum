package com.example.eventum.login.model

import kotlinx.coroutines.flow.MutableStateFlow

data class LoginModel (
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var answer: MutableStateFlow<String> = MutableStateFlow("") // show the user if smth isn't correct
)