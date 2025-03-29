package com.example.eventum.screen_profile.presentation.event

sealed class ProfileEvent {
    class EditName(val name: String): ProfileEvent()
    class EditEmail(val email: String): ProfileEvent()
    class EditPicture(): ProfileEvent()
}