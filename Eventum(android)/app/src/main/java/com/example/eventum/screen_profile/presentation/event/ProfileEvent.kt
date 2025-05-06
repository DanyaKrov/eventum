package com.example.eventum.screen_profile.presentation.event

import com.example.eventum.domain.model.User

sealed class ProfileEvent {
    class UpdateUser(val user: User): ProfileEvent()
}