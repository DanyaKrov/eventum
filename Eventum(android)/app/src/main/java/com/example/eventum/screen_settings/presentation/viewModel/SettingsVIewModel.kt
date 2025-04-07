package com.example.eventum.screen_settings.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat.NotificationVisibility
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.common.Constants
import com.example.eventum.data.local.preferences.SettingsPreferences
import com.example.eventum.data.local.preferences.UserPreferences
import com.example.eventum.data.local.preferences.model.WishListVisibility
import com.example.eventum.screen_settings.domain.model.SettingsModel
import com.example.eventum.screen_settings.domain.useCase.UpdateWishListVisibilityUseCase
import com.example.eventum.screen_settings.presentation.event.SettingsEvent
import com.example.eventum.screen_settings.presentation.event.SettingsNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsVIewModel @Inject constructor(
    private val settingsPreferences: SettingsPreferences,
    private val userPreferences: UserPreferences,
    private val updateWishListVisibilityUseCase: UpdateWishListVisibilityUseCase
): ViewModel() {
    private val _model = mutableStateOf(SettingsModel())
    val model: State<SettingsModel> = _model

    private val navigationStatus: MutableSharedFlow<String> = MutableStateFlow("")
    val navigationStatusRead: SharedFlow<String> = navigationStatus

    init {
        getSettings()
    }

    private fun getSettings() {
        settingsPreferences.darkModeFlow
            .filterNotNull()
            .onEach { theme -> _model.value = SettingsModel(isThemeDark = theme) }
            .launchIn(viewModelScope)
        settingsPreferences.wishListVisibilityFLow
            .filterNotNull()
            .onEach { visibility -> _model.value = SettingsModel(wishListVisibility = visibility) }
            .launchIn(viewModelScope)
    }

    fun handleEvent(event: SettingsEvent) {
        when(event) {
            is SettingsEvent.EditAppTheme -> editTheme(event.theme)
            is SettingsEvent.EditWishListVisibility -> editVisibility(event.visibility)
        }
    }

    private fun editTheme(theme: String) {
        viewModelScope.launch {
            settingsPreferences.saveDarkMode(theme == "dark")
        }
    }

    private fun editVisibility(visibility: String) {
        viewModelScope.launch {
            settingsPreferences.saveWishListVisibility(visibility)
        }
        userPreferences.userIdFlow
            .filterNotNull()
            .onEach {
                updateWishListVisibilityUseCase(it, visibility == WishListVisibility.OPEN_VIEW.value)
            }
            .launchIn(viewModelScope)
    }

    fun handleNavigationEvent(event: SettingsNavigationEvent) {
        when(event) {
            is SettingsNavigationEvent.MoveBackEvent -> moveBack()
        }
    }

    private fun moveBack() {
        viewModelScope.launch {
            navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_PROFILE_PAGE)
        }
    }
}