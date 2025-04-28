package com.example.eventum.screen_signUp.domain.model

import kotlinx.coroutines.flow.MutableStateFlow

data class SignUpModel(
    var name: String = "",
    var email: String = "",
    var password: String? = null,
    var secondPassword: String? = null,
    var requirementsStatement: MutableStateFlow<String> = MutableStateFlow("")
)
