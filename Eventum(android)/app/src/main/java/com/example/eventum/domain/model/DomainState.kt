package com.example.eventum.domain.model

data class DomainState( // state of domain level operation
    val isSuccess: Boolean? = null,
    val errorMessage: String? = null
    // yeah, yeah, it is the same as UiState class
    // but anyway they are separated due to purpose
)
