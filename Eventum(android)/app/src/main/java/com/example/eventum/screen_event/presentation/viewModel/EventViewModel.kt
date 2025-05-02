package com.example.eventum.screen_event.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.common.Constants
import com.example.eventum.data.local.preferences.EventPreferences
import com.example.eventum.data.local.preferences.UserPreferences
import com.example.eventum.domain.model.Resource
import com.example.eventum.domain.model.UiState
import com.example.eventum.feature_notifications.model.Notification
import com.example.eventum.screen_event.domain.model.EventModel
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.domain.model.NotificationsModel
import com.example.eventum.screen_event.domain.repository.EventRepository
import com.example.eventum.screen_event.domain.useCase.GetEventUseCase
import com.example.eventum.screen_event.domain.useCase.GetNotificationsUseCase
import com.example.eventum.screen_event.domain.useCase.UpdateEventUseCase
import com.example.eventum.screen_event.domain.useCase.UpdateNotificationUseCase
import com.example.eventum.screen_event.presentation.event.EventPageEvent
import com.example.eventum.screen_event.presentation.event.EventPageNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventViewModel @Inject constructor(
    private val getEventUseCase: GetEventUseCase,
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val updateEventUseCase: UpdateEventUseCase,
    private val updateNotificationsUseCase: UpdateNotificationUseCase,
    private val eventPreferences: EventPreferences,
    private val repository: EventRepository
): ViewModel() {
    // navigation parameters
    private val _navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatus: StateFlow<String> = _navigationStatus

    private val _eventModel = mutableStateOf(EventModel(uiState = UiState(isLoading = true))) // mutable state of model
    val eventModel: State<EventModel> = _eventModel // immutable state of model to presentation layer

    private val _notificationsModel = mutableStateOf(NotificationsModel()) // mutable state of model
    val notificationsModel: State<NotificationsModel> = _notificationsModel // immutable state of model to presentation layer


    init {
        setModel()
    }

    private fun setModel() {
        eventPreferences.eventIdFlow // flow of event's id, after it flow of event, after it flow of notifications
            .filterNotNull()
            .flatMapLatest { eventId ->
                getEventUseCase(eventId)
                    .filterNotNull()
                    .onEach { event ->
                        _eventModel.value = EventModel(uiState = UiState(isLoading = false),
                            event=event,) }
                    .flatMapLatest { event ->
                        getNotificationsUseCase(event)
                    }
                    .filterNotNull()
                    .onEach { result ->
                        when(result) {
                            is Resource.Success ->
                            {
                                _notificationsModel.value = NotificationsModel(
                                    uiState = UiState(isLoading = false),
                                    notifications = result.data ?: mutableListOf()
                                )
                            }
                            is Resource.Loading -> {
                                _notificationsModel.value = NotificationsModel(
                                    uiState = UiState(isLoading = true)
                                )
                            }
                            is Resource.Error -> {
                                _notificationsModel.value = NotificationsModel(
                                    uiState = UiState(isLoading = false,
                                        errorMessage = result.message ?: "An unexpected error occurred")
                                )
                            }
                        }
                    }
            }
            .launchIn(viewModelScope)
    }


    fun handleEvent(event: EventPageEvent) {
        when(event) {
            is EventPageEvent.AddUserEvent -> addUser()
            is EventPageEvent.DeleteNotification -> deleteNotification(event.notification)
            is EventPageEvent.EditEvent -> editEvent()
            is EventPageEvent.EditNotification -> editNotification(event.notification)
        }
    }

    private fun deleteNotification(notification: NotificationModel) {
        viewModelScope.launch { repository.deleteNotification(notification) }
    }

    private fun editNotification(notification: NotificationModel) {
        // start dialogue with it
    }


    private fun editEvent() {
        // start dialogue with it
    }

    private fun addUser() {
        // start dialogue with it
    }


    fun handleNavigationEvent(event: EventPageNavigationEvent) {
        when(event) {
            is EventPageNavigationEvent.MoveBack -> _navigationStatus.value = Constants.NAVIGATION_MOVE_TO_MAIN_PAGE
        }
    }
}