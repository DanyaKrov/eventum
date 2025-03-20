package com.example.eventum.screen_mainPage.presentation.viewModel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.data.local.preferences.EventPreferences
import com.example.eventum.data.local.preferences.UserPreferences
import com.example.eventum.domain.model.Resource
import com.example.eventum.domain.model.User
import com.example.eventum.domain.useCase.GetUserUseCase
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.model.MainPageModel
import com.example.eventum.screen_mainPage.domain.useCase.DeleteEventUseCase
import com.example.eventum.screen_mainPage.domain.useCase.RefreshEventsUseCase
import com.example.eventum.screen_mainPage.presentation.event.MainPageEvent
import com.example.eventum.screen_mainPage.presentation.event.MainPageNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val refreshEventsUseCase: RefreshEventsUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val eventPreferences: EventPreferences,
    private val userPreferences: UserPreferences
): ViewModel() {
    private val _model = mutableStateOf(MainPageModel()) // mutable model, only for viewModel
    val model = _model // immutable model for composable functions
    // navigation parameters
    private val navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatusRead: StateFlow<String> = navigationStatus

    init {
        getUser()
            .filterNotNull()
            .onEach { result -> when(result) {
                is Resource.Success -> {
                    refreshEvents(result.data?.events ?: listOf(), false)
                    userPreferences.saveUserId(result.data?.remoteId ?: 0)
                }
                is Resource.Loading -> {
                    _model.value = MainPageModel(isLoading = true)
                }
                is Resource.Error -> {
                    _model.value = MainPageModel(errorMessage = result.message ?: "An unexpected error occurred")
                }
            } }
            .launchIn(viewModelScope)
    }

    private fun getUser(): Flow<Resource<User>> {
        return getUserUseCase()
    }

    private suspend fun refreshEvents(eventsIds: List<Long>, refreshLocal: Boolean) {
        _model.value.events.clear()
        refreshEventsUseCase.invoke(eventsIds, refreshLocal).forEach {
            _model.value.events.add(it) }
    }

    fun handleEvent(event: MainPageEvent) {
        when(event){
            is MainPageEvent.EventDelete -> startEventDeleteConfirmation(event.selectedEvent)
            is MainPageEvent.EventEdit -> startEventEditWindow(event.selectedEvent)
            is MainPageEvent.EventExpanded -> expandEventView(event.eventNumber)
        }
    }

    private fun expandEventView(eventNumber: Long) {}

    private fun startEventDeleteConfirmation(event: Event) {
        // confirmation dialogue window
        eventDelete(event)
    }

    private fun eventDelete(event: Event) {
        _model.value.events.remove(event)
        viewModelScope.launch {
            deleteEventUseCase.invoke(event)
        }
    }


    private fun startEventEditWindow(event: Event) {
        viewModelScope.launch {
            eventPreferences.saveEventId(event.remoteId)
        }
    }


    fun handleNavigation(event: MainPageNavigationEvent) {
        when(event){
            is MainPageNavigationEvent.ChangeToCalendarView -> changeToCalendarView()
            is MainPageNavigationEvent.NavigateToPreparationsPage -> TODO()
            is MainPageNavigationEvent.NavigateToProfilePage -> TODO()
            is MainPageNavigationEvent.NavigateToSettings -> TODO()
        }
    }

    private fun changeToCalendarView() { // page of events, but as calendar not as list
    }

}