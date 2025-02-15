package com.example.eventum.login.api.model

data class AuthRequest(
    var email: String = "",
    var password: String = "",
    var jwtToken: String = "" // need to add token system
)
