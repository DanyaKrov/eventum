package com.example.eventum.screen_mainPage.presentation.viewModel


import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.common.Constants
import com.example.eventum.data.local.preferences.EventPreferences
import com.example.eventum.data.local.preferences.UserPreferences
import com.example.eventum.domain.model.DomainState
import com.example.eventum.domain.model.Resource
import com.example.eventum.domain.model.UiState
import com.example.eventum.domain.model.User
import com.example.eventum.screen_contacts.domain.model.ContactsModel
import com.example.eventum.screen_mainPage.domain.useCase.GetCurrentUserUseCase
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.screen_mainPage.domain.model.EventRequestModel
import com.example.eventum.screen_mainPage.domain.model.MainPageModel
import com.example.eventum.screen_mainPage.domain.useCase.CreateEventUseCase
import com.example.eventum.screen_mainPage.domain.useCase.DeleteEventUseCase
import com.example.eventum.screen_mainPage.domain.useCase.RefreshEventsUseCase
import com.example.eventum.screen_mainPage.domain.useCase.SelectEventUseCase
import com.example.eventum.screen_mainPage.presentation.event.MainPageEvent
import com.example.eventum.screen_mainPage.presentation.event.MainPageNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val refreshEventsUseCase: RefreshEventsUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
    private val createEventUseCase: CreateEventUseCase,
    private val userPreferences: UserPreferences,
    private val selectEventUseCase: SelectEventUseCase
): ViewModel() {
    private val _model = mutableStateOf(MainPageModel()) // mutable model, only for viewModel
    val model: State<MainPageModel> = _model // immutable model for composable functions
    // navigation parameters
    private val navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatusRead: StateFlow<String> = navigationStatus

    private val _eventCreationStatus = mutableStateOf(DomainState())
    val eventCreationStatus: State<DomainState> = _eventCreationStatus

    private var userRemoteId: Long? = null // not good way, find out where to store id for quickest approach


    init {
        getEvents()
    }

    private fun getEvents() {
        userPreferences.userIdFlow // state of user Id
            .onEach { userId ->
                if (userId == null) // it means no userId presented at the moment
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_LOGIN_PAGE)
                userRemoteId = userId
            }
            .filterNotNull()
            .flatMapLatest { userId ->
                refreshEventsUseCase(userId, false) // if id changes, contacts update
            }
            .onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _model.value = MainPageModel(events = result.data?.toMutableList() ?: mutableListOf())
                    }
                    is Resource.Loading -> {
                        _model.value = MainPageModel(UiState(isLoading = true))
                    }
                    is Resource.Error -> {
                        _model.value = MainPageModel(UiState(errorMessage = result.message ?: "An unexpected error occurred"))
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun handleEvent(event: MainPageEvent) {
        when(event){
            is MainPageEvent.EventDelete -> eventDelete(event.selectedEvent)
            is MainPageEvent.EventExpanded -> expandEventView(event.selectedEvent)
            is MainPageEvent.EventCreate -> createEvent(event.createdEvent)
        }
    }

    private fun createEvent(event: EventRequestModel) {
        createEventUseCase(userRemoteId ?: 0, event)
            .filterNotNull()
            .onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _model.value.events.add(it)
                        }
                        _eventCreationStatus.value = DomainState(isSuccess = true)
                    }
                    is Resource.Loading -> {
                        // just wait, but need to handle if it takes too long
                    }
                    is Resource.Error -> {
                        _eventCreationStatus.value = DomainState(isSuccess = false)
                    }
                }
            }.launchIn(viewModelScope)
        getEvents() // reload model
    }

    private fun expandEventView(selectedEvent: Event) {
        viewModelScope.launch {
            selectEventUseCase(selectedEvent)
            navigationStatus.value = Constants.NAVIGATION_MOVE_TO_EVENT_PAGE
        }
    }

    private fun eventDelete(event: Event) {
        viewModelScope.launch {
            deleteEventUseCase.invoke(event)
        }
        getEvents()
    }


    fun handleNavigation(event: MainPageNavigationEvent) {
        when(event){
            is MainPageNavigationEvent.ChangeToCalendarView -> changeToCalendarView()
            is MainPageNavigationEvent.NavigateToEventPage -> navigateToEventPage()
            is MainPageNavigationEvent.NavigateToProfilePage -> navigateToProfilePage()
        }
    }

    private fun navigateToProfilePage() {
        viewModelScope.launch {
            navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_PROFILE_PAGE)
        }
    }

    private fun navigateToEventPage() {
        viewModelScope.launch {
            navigationStatus.value = Constants.NAVIGATION_MOVE_TO_EVENT_PAGE
        }
    }

    private fun changeToCalendarView() { // page of events, but as calendar not as list
    }

}