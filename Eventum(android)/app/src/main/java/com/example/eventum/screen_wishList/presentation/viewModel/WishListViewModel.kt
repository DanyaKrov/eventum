package com.example.eventum.screen_wishList.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.data.local.preferences.WishListPreferences
import com.example.eventum.domain.model.Resource
import com.example.eventum.domain.model.UiState
import com.example.eventum.screen_wishList.domain.model.WishListModel
import com.example.eventum.screen_wishList.domain.useCase.RefreshWishListUseCase
import com.example.eventum.screen_wishList.presentation.event.WishListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val wishListPreferences: WishListPreferences,
    private val refreshWishListUseCase: RefreshWishListUseCase
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
        wishListPreferences.wishListIdFlow
            .filterNotNull()
            .flatMapLatest { wishListId ->
                refreshWishListUseCase(wishListId)
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
            is WishListEvent.CreatePresentEvent -> TODO()
            is WishListEvent.EditPresentEvent -> TODO()
        }
    }
}