package com.example.eventum.signUp.model

import kotlinx.coroutines.flow.MutableStateFlow

data class SignUpModel(
    var name: String? = null,
    var email: String = "",
    var password: String? = null,
    var secondPassword: String? = null,
    var requirementsStatement: MutableStateFlow<String> = MutableStateFlow("")
)
