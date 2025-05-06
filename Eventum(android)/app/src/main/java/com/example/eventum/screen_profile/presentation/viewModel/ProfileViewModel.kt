package com.example.eventum.screen_profile.presentation.viewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.common.Constants
import com.example.eventum.data.local.preferences.UserPreferences
import com.example.eventum.domain.model.Operation
import com.example.eventum.domain.model.Resource
import com.example.eventum.domain.model.UiState
import com.example.eventum.domain.model.User
import com.example.eventum.screen_mainPage.domain.useCase.GetCurrentUserUseCase
import com.example.eventum.screen_presents.domain.model.PresentsModel
import com.example.eventum.screen_profile.domain.model.ProfileModel
import com.example.eventum.screen_profile.domain.useCase.UpdateUserUseCase
import com.example.eventum.screen_profile.presentation.event.ProfileEvent
import com.example.eventum.screen_profile.presentation.event.ProfileNavigationEvent
import com.example.eventum.screen_wishList.domain.model.WishListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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
                                Log.i("info", model.value.toString())
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
            is ProfileEvent.UpdateUser -> updateUser(event.user)
        }
    }

    private fun updateUser(user: User) {
        executeOperation(updateUserUseCase(user))
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

    fun handleNavigationEvent(event: ProfileNavigationEvent) {
        viewModelScope.launch {
            when(event) {
                is ProfileNavigationEvent.NavigateToContactsPage ->
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_CONTACTS_PAGE)
                is ProfileNavigationEvent.ExitFromAccount -> {
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_HELLO_PAGE)
                    userPreferences.clearUserId()
                }
                is ProfileNavigationEvent.NavigateToMainPage ->
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_MAIN_PAGE)
                is ProfileNavigationEvent.NavigateToSettingsPage ->
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_SETTINGS_PAGE)
                is ProfileNavigationEvent.NavigateToWishListPage ->
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_WISHLIST_PAGE)
            }
        }
    }

    private fun executeOperation(flow: Flow<Operation>) {
        flow
            .filterNotNull()
            .onEach { result ->
                when (result) {
                    is Operation.Success -> {
                        getUserInformation()
                    }

                    is Operation.Loading -> {
                        _model.value = ProfileModel(
                            user = model.value.user,
                            uiState = UiState(isLoading = true)
                        )
                    }

                    is Operation.Error -> {
                        _model.value = ProfileModel(
                            user = model.value.user,
                            uiState = UiState(isLoading = false,
                                errorMessage = result.message ?: "An unexpected error occurred")
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}