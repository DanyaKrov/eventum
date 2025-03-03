package com.example.eventum.screen_mainPage.domain.model

data class MainPageModel(
    val isLoading: Boolean = false,
    val events: MutableList<Event> = mutableListOf(),
    val errorMessage: String = ""
)