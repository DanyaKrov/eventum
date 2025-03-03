package com.example.eventum.screen_presents.domain.model

data class PresentsModel(
    val isLoading: Boolean = false,
    val presents: MutableList<Present> = mutableListOf(),
    val errorMessage: String = ""
)