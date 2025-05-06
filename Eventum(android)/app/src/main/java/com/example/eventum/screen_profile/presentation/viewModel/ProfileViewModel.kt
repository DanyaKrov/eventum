package com.example.eventum.screen_profile.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.common.Constants
import com.example.eventum.data.local.preferences.UserPreferences
import com.example.eventum.domain.model.Resource
import com.example.eventum.domain.model.UiState
import com.example.eventum.screen_mainPage.domain.useCase.GetCurrentUserUseCase
import com.example.eventum.screen_presents.domain.model.PresentsModel
import com.example.eventum.screen_profile.domain.model.ProfileModel
import com.example.eventum.screen_profile.domain.useCase.UpdateUserUseCase
import com.example.eventum.screen_profile.presentation.event.ProfileEvent
import com.example.eventum.screen_profile.presentation.event.ProfileNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val userPreferences: UserPreferences
): ViewModel() {
    private val _model = mutableStateOf(ProfileModel())
    val model: State<ProfileModel> = _model

    // parameters for image picker activity
    private val _editImageEvent = MutableStateFlow<Unit?>(null)
    val editImageEvent: StateFlow<Unit?> = _editImageEvent

    private val navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatusRead: StateFlow<String> = navigationStatus


    init {
        getUserInformation()
    }

    private fun getUserInformation() {
        userPreferences.userIdFlow
            .filterNotNull()
            .flatMapLatest { characters ->
                getCurrentUserUseCase(characters)
                    .filterNotNull()
                    .onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                result.data?.let { user ->
                                    _model.value = ProfileModel(user = user)
                                }
                            }
                            is Resource.Loading -> {
                                _model.value = ProfileModel(uiState = UiState(isLoading = true))
                            }
                            is Resource.Error -> {
                                _model.value = ProfileModel(
                                    uiState = UiState(errorMessage = result.message ?: "An unexpected error occurred")
                                )
                            }
                        }
                    }
            }
            .launchIn(viewModelScope)
    }

    fun handleEvent(event: ProfileEvent) {
        when(event) {
            is ProfileEvent.EditEmail -> editEmail(event.email)
            is ProfileEvent.EditName -> editName(event.name)
            is ProfileEvent.EditPicture -> editPicture()
        }
    }

    private fun editPicture() {
        viewModelScope.launch {
            _editImageEvent.emit(Unit)
        // ui will get change of state and start gallery activity
        }
    }

    fun onImageSelected(uri: Uri) {
        // saving pictures for users. At remote and local levels
    }

    private fun editName(newName: String) {
        _model.value.user?.let { user ->
            user.name = newName
        }
    }

    private fun editEmail(newEmail: String) {
        _model.value.user?.let { user ->
            user.email = newEmail
        }
    }

    fun handleNavigationEvent(event: ProfileNavigationEvent) {
        viewModelScope.launch {
            when(event) {
                is ProfileNavigationEvent.NavigateToContactsPage ->
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_CONTACTS_PAGE)
                is ProfileNavigationEvent.NavigateToLogInPage ->
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_LOGIN_PAGE)
                is ProfileNavigationEvent.NavigateToMainPage ->
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_MAIN_PAGE)
                is ProfileNavigationEvent.NavigateToSettingsPage ->
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_SETTINGS_PAGE)
                is ProfileNavigationEvent.NavigateToWishListPage ->
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_WISHLIST_PAGE)
            }
        }
    }
}