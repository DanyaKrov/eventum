package com.example.eventum.screen_wishList.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import com.example.eventum.screen_wishList.domain.useCase.RefreshWishListUseCase
import com.example.eventum.screen_wishList.presentation.event.WishListEvent
import com.example.eventum.screen_wishList.presentation.event.WishListNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val addPresentUseCase: AddPresentUseCase
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
        userPreferences.userIdFlow
            .filterNotNull()
            .flatMapLatest { userId ->
                refreshWishListUseCase(userId)
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
            }
    }

    fun handleEvent(event: WishListEvent) {
        when (event) {
            is WishListEvent.ChangeOrderEvent -> TODO()
            is WishListEvent.ChangeVisibility -> TODO()
            is WishListEvent.CreatePresent -> createPresent(event.present)
            is WishListEvent.DeletePresent -> TODO()
            is WishListEvent.UpdatePresent -> TODO()
        }
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

    fun handleNavigation(event: WishListNavigationEvent) {
        when(event) {
            is WishListNavigationEvent.NavigateToContactsPage -> TODO()
            is WishListNavigationEvent.NavigateToMainPage -> TODO()
            is WishListNavigationEvent.NavigateToProfilePage -> TODO()
        }
    }
}