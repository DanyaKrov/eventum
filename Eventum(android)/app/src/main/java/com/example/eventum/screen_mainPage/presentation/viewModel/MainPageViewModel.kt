package com.example.eventum.screen_mainPage.presentation.viewModel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.data.roomDatabase.entity.UserEntity
import com.example.eventum.data.roomDatabase.repository.UserLocalRepository
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.model.MainPageModel
import com.example.eventum.screen_mainPage.domain.useCase.DeleteEventUseCase
import com.example.eventum.screen_mainPage.domain.useCase.RefreshEventsUseCase
import com.example.eventum.screen_mainPage.presentation.event.MainPageEvent
import com.example.eventum.screen_mainPage.presentation.event.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val refreshEventsUseCase: RefreshEventsUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
    private val roomUserLocalRepository: UserLocalRepository
): ViewModel() {
    private val _model = mutableStateOf(MainPageModel()) // mutable model, only for viewModel
    val model = _model // immutable model for composable functions

    init {
        viewModelScope.launch {
            val user = getUser()
            refreshEvents(user.events, true)
        }
    }

    private suspend fun getUser(): UserEntity {
        return roomUserLocalRepository.getUser()
    }

    private suspend fun refreshEvents(eventsIds: List<Long>, refreshLocal: Boolean) {
        _model.value.events.clear()
        refreshEventsUseCase.invoke(eventsIds, refreshLocal).forEach {
            _model.value.events.add(it) }
    }

    fun handleEvent(event: MainPageEvent) {
        when(event){
            is MainPageEvent.EventDelete -> startEventDeleteConfirmation(event.event)
            is MainPageEvent.EventEdit -> startEventEditWindow(event.eventNumber)
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


    private fun startEventEditWindow(eventNumber: Long) {

    }


    fun handleNavigation(event: NavigationEvent) {
        when(event){
            is NavigationEvent.ChangeToCalendarView -> changeToCalendarView()
            is NavigationEvent.NavigateToPreparationsPage -> TODO()
            is NavigationEvent.NavigateToProfilePage -> TODO()
            is NavigationEvent.NavigateToSettings -> TODO()
        }
    }

    private fun changeToCalendarView() { // page of events, but as calendar not as list
    }

}