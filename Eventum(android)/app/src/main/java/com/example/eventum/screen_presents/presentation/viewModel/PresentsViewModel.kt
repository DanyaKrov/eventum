package com.example.eventum.screen_presents.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eventum.common.Constants
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_presents.domain.model.PresentsModel
import com.example.eventum.screen_presents.domain.useCase.RefreshPresentsUseCase
import com.example.eventum.screen_presents.presentation.event.PresentsEvent
import com.example.eventum.screen_presents.presentation.event.PresentsNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PresentsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val refreshPresentsUseCase: RefreshPresentsUseCase
): ViewModel() {
    private val _model = mutableStateOf(PresentsModel()) // mutable state of model
    val model = _model // immutable state of model to presentation layer


    init {
        savedStateHandle.get<Long>(Constants.WISHLIST_ID)?.let {
            viewModelScope.launch { getPresents(it) }
        }
    }

    private suspend fun getPresents(wishListId: Long) = refreshPresentsUseCase.invoke(wishListId, false)

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