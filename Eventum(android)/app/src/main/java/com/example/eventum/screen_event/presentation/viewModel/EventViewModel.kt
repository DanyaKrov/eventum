package com.example.eventum.screen_event.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.common.Constants
import com.example.eventum.data.local.preferences.EventPreferences
import com.example.eventum.domain.model.Operation
import com.example.eventum.domain.model.Resource
import com.example.eventum.domain.model.UiState
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_event.domain.model.EventModel
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.domain.model.NotificationsModel
import com.example.eventum.screen_event.domain.useCase.CreateNotificationUseCase
import com.example.eventum.screen_event.domain.useCase.DeleteNotificationUseCase
import com.example.eventum.screen_event.domain.useCase.GetEventUseCase
import com.example.eventum.screen_event.domain.useCase.GetNotificationsUseCase
import com.example.eventum.screen_event.domain.useCase.UpdateEventUseCase
import com.example.eventum.screen_event.domain.useCase.UpdateNotificationUseCase
import com.example.eventum.screen_event.presentation.event.EventPageEvent
import com.example.eventum.screen_event.presentation.event.EventPageNavigationEvent
import com.example.eventum.screen_mainPage.domain.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class EventViewModel @Inject constructor(
    private val getEventUseCase: GetEventUseCase,
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val createNotificationUseCase: CreateNotificationUseCase,
    private val deleteNotificationUseCase: DeleteNotificationUseCase,
    private val updateEventUseCase: UpdateEventUseCase,
    private val updateNotificationsUseCase: UpdateNotificationUseCase,
    private val eventPreferences: EventPreferences,
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
                            event=event) }
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
            is EventPageEvent.AddContact -> addContact(event.contact)
            is EventPageEvent.DeleteNotification -> deleteNotification(event.notification)
            is EventPageEvent.EditEvent -> editEvent(event.event)
            is EventPageEvent.CreateNotification -> createNotification(event.notification)
        }
    }

    private fun deleteNotification(notification: NotificationModel) {
        deleteNotificationUseCase(notification)
            .filterNotNull()
            .onEach { result ->
                when (result) {
                    is Operation.Success -> {
                        setModel()
                    }

                    is Operation.Loading -> {
                        _notificationsModel.value = NotificationsModel(
                            notifications = notificationsModel.value.notifications,
                            uiState = UiState(isLoading = true)
                        )
                    }

                    is Operation.Error -> {
                        _notificationsModel.value = NotificationsModel(
                            notifications = notificationsModel.value.notifications,
                            uiState = UiState(isLoading = false,
                                errorMessage = result.message ?: "An unexpected error occurred")
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun createNotification(notification: NotificationModel) {
        createNotificationUseCase(notification)
            .filterNotNull()
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        setModel()
                    }

                    is Resource.Loading -> {
                        _notificationsModel.value = NotificationsModel(
                            notifications = notificationsModel.value.notifications,
                            uiState = UiState(isLoading = true)
                        )
                    }

                    is Resource.Error -> {
                        _notificationsModel.value = NotificationsModel(
                            notifications = notificationsModel.value.notifications,
                            uiState = UiState(isLoading = false,
                                errorMessage = result.message ?: "An unexpected error occurred")
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun editEvent(updatedEvent: Event) {
        updateEventUseCase(updatedEvent)
            .filterNotNull()
            .onEach { result ->
                when (result) {
                    is Operation.Success -> {
                        setModel()
                    }

                    is Operation.Loading -> {
                        _eventModel.value = EventModel(
                            event = updatedEvent,
                            uiState = UiState(isLoading = true)
                        )
                    }

                    is Operation.Error -> {
                        _eventModel.value = EventModel(
                            event = updatedEvent,
                            uiState = UiState(isLoading = false,
                                errorMessage = result.message ?: "An unexpected error occurred")
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun addContact(contact: Contact) {
        // start dialogue with it
    }


    fun handleNavigationEvent(event: EventPageNavigationEvent) {
        when(event) {
            is EventPageNavigationEvent.MoveBack -> _navigationStatus.value = Constants.NAVIGATION_MOVE_TO_MAIN_PAGE
        }
    }
}