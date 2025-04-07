package com.example.eventum.screen_settings.presentation.event


sealed class SettingsEvent {
    class EditAppTheme(val theme: String) : SettingsEvent()
    class EditWishListVisibility(val visibility: String) : SettingsEvent()
}
