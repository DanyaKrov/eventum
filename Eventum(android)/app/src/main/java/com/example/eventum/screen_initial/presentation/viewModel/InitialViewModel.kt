package com.example.eventum.screen_initial.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.common.Constants
import com.example.eventum.data.local.preferences.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(
    private val userPreferences: UserPreferences
): ViewModel() {
    // navigation parameters
    private val navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatusRead: StateFlow<String> = navigationStatus

    init {
        userPreferences.userIdFlow
            .onEach {
                if (it == null)
                    navigationStatus.value = Constants.NAVIGATION_MOVE_TO_HELLO_PAGE
                else
                    navigationStatus.value = Constants.NAVIGATION_MOVE_TO_MAIN_PAGE
            }
            .launchIn(viewModelScope)
    }
}