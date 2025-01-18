package com.example.eventum.signUp.model

data class User(
    val id: Long,
    var name: String,
    var email: String,
    var picture: String,
    var users : MutableSet<User>
)
