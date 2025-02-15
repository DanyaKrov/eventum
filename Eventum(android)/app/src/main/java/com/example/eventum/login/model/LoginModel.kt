package com.example.eventum.login.model

import kotlinx.coroutines.flow.MutableStateFlow

data class LoginModel (
    var email: String = "",
    var password: String = "",
    var response: MutableStateFlow<String> = MutableStateFlow("") // show the user if smth isn't correct
)