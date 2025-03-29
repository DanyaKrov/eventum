package com.example.eventum.screen_presents.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eventum.common.Constants
import com.example.eventum.data.local.preferences.WishListPreferences
import com.example.eventum.domain.model.Resource
import com.example.eventum.screen_contacts.domain.model.ContactsModel
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_presents.domain.model.PresentsModel
import com.example.eventum.screen_presents.domain.useCase.RefreshPresentsUseCase
import com.example.eventum.screen_presents.presentation.event.PresentsEvent
import com.example.eventum.screen_presents.presentation.event.PresentsNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PresentsViewModel @Inject constructor(
    private val wishListPreferences: WishListPreferences,
    private val refreshPresentsUseCase: RefreshPresentsUseCase
): ViewModel() {
    private val _model = mutableStateOf(PresentsModel()) // mutable state of model
    val model: State<PresentsModel> = _model // immutable state of model to presentation layer

    private val navigationStatus: MutableSharedFlow<String> = MutableStateFlow("")
    val navigationStatusRead: SharedFlow<String> = navigationStatus


    init {
        getPresents()
    }

    private fun getPresents() {
        wishListPreferences.wishListIdFlow
            .filterNotNull()
            .flatMapLatest { wishListId ->
                refreshPresentsUseCase(wishListId, false)
            }
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _model.value = PresentsModel(presents = result.data ?: mutableListOf())
                    }

                    is Resource.Loading -> {
                        _model.value = PresentsModel(isLoading = true)
                    }

                    is Resource.Error -> {
                        _model.value = PresentsModel(
                            errorMessage = result.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun handleEvent(event: PresentsEvent) {
        when(event){
            is PresentsEvent.SortPresentsEvent -> sortPresents()
            is PresentsEvent.EditPresentEvent -> editPresent(event.present)
            is PresentsEvent.AddPresentEvent -> addPresent()
        }
    }


    fun handleNavigation(navigationEvent: PresentsNavigationEvent) {
        when(navigationEvent){
            is PresentsNavigationEvent.NavigateBack -> navigateBack()
        }
    }


    private fun navigateBack() {

    }


    private fun sortPresents() {
    }


    private fun editPresent(present: Present) {
    }

    private fun addPresent() {}
}