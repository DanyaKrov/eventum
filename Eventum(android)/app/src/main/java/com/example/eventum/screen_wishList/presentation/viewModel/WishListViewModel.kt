package com.example.eventum.screen_wishList.presentation.viewModel

import android.opengl.Visibility
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.common.Constants
import com.example.eventum.data.local.preferences.UserPreferences
import com.example.eventum.data.local.preferences.WishListPreferences
import com.example.eventum.domain.model.Operation
import com.example.eventum.domain.model.Resource
import com.example.eventum.domain.model.UiState
import com.example.eventum.screen_contacts.presentation.event.ContactsNavigationEvent
import com.example.eventum.screen_event.domain.model.EventModel
import com.example.eventum.screen_event.domain.model.NotificationsModel
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_wishList.domain.model.WishListModel
import com.example.eventum.screen_wishList.domain.useCase.AddPresentUseCase
import com.example.eventum.screen_wishList.domain.useCase.ChangeVisibilityUseCase
import com.example.eventum.screen_wishList.domain.useCase.DeletePresentUseCase
import com.example.eventum.screen_wishList.domain.useCase.RefreshWishListUseCase
import com.example.eventum.screen_wishList.domain.useCase.UpdatePresentUseCase
import com.example.eventum.screen_wishList.presentation.event.WishListEvent
import com.example.eventum.screen_wishList.presentation.event.WishListNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
    private val refreshWishListUseCase: RefreshWishListUseCase,
    private val addPresentUseCase: AddPresentUseCase,
    private val deletePresentUseCase: DeletePresentUseCase,
    private val updatePresentUseCase: UpdatePresentUseCase,
    private val changeVisibilityUseCase: ChangeVisibilityUseCase
): ViewModel() {
    // navigation parameters
    private val navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatusRead: StateFlow<String> = navigationStatus
    private val _model = mutableStateOf(WishListModel())
    val model: State<WishListModel> = _model

    init {
        getWishList()
    }

    private fun getWishList() {
        userPreferences.userIdFlow // state of user Id
            .onEach { userId ->
                if (userId == null) // it means no userId presented at the moment
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_LOGIN_PAGE)
            }
            .filterNotNull()
            .flatMapLatest { userId ->
                refreshWishListUseCase(userId) // if id changes, contacts update
            }
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { wishListResult ->
                            _model.value = WishListModel(wishList = wishListResult)
                        }
                    }
                    is Resource.Loading -> {
                        _model.value = WishListModel(uiState = UiState(isLoading = true))
                    }

                    is Resource.Error -> {
                        _model.value = WishListModel(
                            uiState = UiState(errorMessage = result.message ?: "An unexpected error occurred")
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun handleEvent(event: WishListEvent) {
        when (event) {
            is WishListEvent.ChangeVisibility -> updateVisibility(event.visibility)
            is WishListEvent.CreatePresent -> createPresent(event.present)
            is WishListEvent.DeletePresent -> deletePresent(event.present)
            is WishListEvent.UpdatePresent -> updatePresent(event.present)
        }
    }

    private fun updateVisibility(visibility: Boolean) {
        userPreferences.userIdFlow
            .filterNotNull()
            .flatMapLatest { userId ->
                changeVisibilityUseCase(userId, visibility)
                    .filterNotNull()
                    .onEach { result ->
                        when (result) {
                            is Operation.Success -> {
                                _model.value = WishListModel(
                                    wishList = model.value.wishList?.copy(
                                        visibility = visibility
                                    ),
                                    uiState = UiState(isLoading = true)
                                )
                            }

                            is Operation.Loading -> {
                                _model.value = WishListModel(
                                    wishList = model.value.wishList,
                                    uiState = UiState(isLoading = true)
                                )
                            }

                            is Operation.Error -> {
                                _model.value = WishListModel(
                                    wishList = model.value.wishList,
                                    uiState = UiState(isLoading = false,
                                        errorMessage = result.message ?: "An unexpected error occurred")
                                )
                            }
                        }
                    }
            }.launchIn(viewModelScope)
    }


    private fun updatePresent(present: Present) {
        executeOperation(updatePresentUseCase(present))
    }


    private fun deletePresent(present: Present) {
        executeOperation(deletePresentUseCase(present))
    }

    private fun createPresent(present: Present) {
        userPreferences.userIdFlow
            .filterNotNull()
            .flatMapLatest { userId ->
                    addPresentUseCase(userId, present)
                        .filterNotNull()
                        .onEach { result ->
                            when (result) {
                                is Operation.Success -> {
                                    getWishList()
                                }

                                is Operation.Loading -> {
                                    _model.value = WishListModel(
                                        wishList = model.value.wishList,
                                        uiState = UiState(isLoading = true)
                                    )
                                }

                                is Operation.Error -> {
                                    _model.value = WishListModel(
                                        wishList = model.value.wishList,
                                        uiState = UiState(isLoading = false,
                                            errorMessage = result.message ?: "An unexpected error occurred")
                                    )
                                }
                            }
                        }
            }.launchIn(viewModelScope)
    }


    private fun executeOperation(flow: Flow<Operation>) {
        flow
            .filterNotNull()
            .onEach { result ->
                when (result) {
                    is Operation.Success -> {
                        getWishList()
                    }

                    is Operation.Loading -> {
                        _model.value = WishListModel(
                            wishList = model.value.wishList,
                            uiState = UiState(isLoading = true)
                        )
                    }

                    is Operation.Error -> {
                        _model.value = WishListModel(
                            wishList = model.value.wishList,
                            uiState = UiState(isLoading = false,
                                errorMessage = result.message ?: "An unexpected error occurred")
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun handleNavigation(event: WishListNavigationEvent) {
        when(event) {
            is WishListNavigationEvent.NavigateToContactsPage -> navigationStatus.value = Constants.NAVIGATION_MOVE_TO_CONTACTS_PAGE
            is WishListNavigationEvent.NavigateToMainPage -> navigationStatus.value = Constants.NAVIGATION_MOVE_TO_MAIN_PAGE
            is WishListNavigationEvent.NavigateToProfilePage -> navigationStatus.value = Constants.NAVIGATION_MOVE_TO_PROFILE_PAGE
        }
    }

}