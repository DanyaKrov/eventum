package com.example.eventum.screen_giftList.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.eventum.data.local.preferences.GiftListPreferences
import com.example.eventum.domain.model.Resource
import com.example.eventum.domain.model.UiState
import com.example.eventum.screen_giftList.domain.model.GiftListModel
import com.example.eventum.screen_giftList.domain.useCase.RefreshGiftList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GiftListViewModel @Inject constructor(
    private val refreshGiftList: RefreshGiftList,
    private val giftListPreferences: GiftListPreferences
): ViewModel() {
    // navigation parameters
    private val navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatusRead: StateFlow<String> = navigationStatus
    private val _model = mutableStateOf(GiftListModel())
    val model: State<GiftListModel> = _model

    init {
        getGiftList()
    }

    private fun getGiftList() {
        giftListPreferences.giftListIdFlow
            .filterNotNull()
            .flatMapLatest { giftListId ->
                refreshGiftList(giftListId)
            }
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { giftsResult ->
                            _model.value = GiftListModel(gifts = giftsResult.toMutableList())
                        }
                    }
                    is Resource.Loading -> {
                        _model.value = GiftListModel(uiState = UiState(isLoading = true))
                    }

                    is Resource.Error -> {
                        _model.value = GiftListModel(
                            uiState = UiState(errorMessage = result.message ?: "An unexpected error occurred")
                        )
                    }
                }
            }
    }
}