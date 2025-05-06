package com.example.eventum.screen_event.presentation.viewModel

import android.util.Log
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
import com.example.eventum.screen_contacts.domain.model.ContactsModel
import com.example.eventum.screen_contacts.domain.useCase.GetContactsUseCase
import com.example.eventum.screen_event.domain.model.EventModel
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.domain.model.NotificationsModel
import com.example.eventum.screen_event.domain.useCase.AddContactUseCase
import com.example.eventum.screen_event.domain.useCase.CreateNotificationUseCase
import com.example.eventum.screen_event.domain.useCase.DeleteNotificationUseCase
import com.example.eventum.screen_event.domain.useCase.GetEventContactsUseCase
import com.example.eventum.screen_event.domain.useCase.GetEventUseCase
import com.example.eventum.screen_event.domain.useCase.GetNotificationsUseCase
import com.example.eventum.screen_event.domain.useCase.RemoveContactUseCase
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
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventViewModel @Inject constructor(
    private val getEventUseCase: GetEventUseCase,
    private val getNotificationsUseCase: GetNotificationsUseCase,

    private val getAvailableContacts: GetContactsUseCase,
    private val getEventContacts: GetEventContactsUseCase,
    private val addContactUseCase: AddContactUseCase,
    private val removeContactUseCase: RemoveContactUseCase,

    private val createNotificationUseCase: CreateNotificationUseCase,
    private val deleteNotificationUseCase: DeleteNotificationUseCase,
    private val updateEventUseCase: UpdateEventUseCase,
    private val updateNotificationUseCase: UpdateNotificationUseCase,
    private val eventPreferences: EventPreferences,
): ViewModel() {
    // navigation parameters
    private val _navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatus: StateFlow<String> = _navigationStatus

    private val _eventModel = mutableStateOf(EventModel(uiState = UiState(isLoading = true))) // mutable state of model
    val eventModel: State<EventModel> = _eventModel // immutable state of model to presentation layer

    private val _notificationsModel = mutableStateOf(NotificationsModel()) // mutable state of model
    val notificationsModel: State<NotificationsModel> = _notificationsModel // immutable state of model to presentation layer


    private val _contactsModel = mutableStateOf(ContactsModel(isLoading = true))
    val contactsModel: State<ContactsModel> = _contactsModel


    private val _availableContactsModel = mutableStateOf(ContactsModel(isLoading = true))
    val availableContactsModel: State<ContactsModel> = _availableContactsModel


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
                                setContactsModel()
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

    private fun setContactsModel() {
        getAllContacts()
        eventModel.value.event?.let {
            getEventContacts(it)
                .filterNotNull()
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            _contactsModel.value = ContactsModel(
                                isLoading = false,
                                contacts = result.data ?: mutableListOf()
                            )
                            Log.i("testing", contactsModel.value.contacts.toString())
                        }

                        is Resource.Loading -> {
                            _contactsModel.value = ContactsModel(
                                isLoading = true
                            )
                        }

                        is Resource.Error -> {
                            _contactsModel.value = ContactsModel(
                                isLoading = false,
                                    errorMessage = result.message ?: "An unexpected error occurred")
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }


    fun handleEvent(event: EventPageEvent) {
        when(event) {
            is EventPageEvent.AddContact -> addContact(event.contact)
            is EventPageEvent.DeleteNotification -> deleteNotification(event.notification)
            is EventPageEvent.EditEvent -> editEvent(event.event)
            is EventPageEvent.CreateNotification -> createNotification(event.notification)
            is EventPageEvent.EditNotification -> updateNotification(event.updatedNotification)
        }
    }

    private fun updateNotification(updatedNotification: NotificationModel) {
        updateNotificationUseCase(updatedNotification)
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
                            event = eventModel.value.event,
                            uiState = UiState(isLoading = true)
                        )
                    }

                    is Operation.Error -> {
                        _eventModel.value = EventModel(
                            event = eventModel.value.event,
                            uiState = UiState(isLoading = false,
                                errorMessage = result.message ?: "An unexpected error occurred")
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun addContact(contact: Contact) {
        eventModel.value.event?.let {
            addContactUseCase(it, contact)
                .filterNotNull()
                .onEach { result ->
                    when (result) {
                        is Operation.Success -> {
                            setContactsModel()
                        }

                        is Operation.Loading -> {
                            _contactsModel.value = ContactsModel(isLoading = true)
                        }

                        is Operation.Error -> {
                            _contactsModel.value = ContactsModel(
                                errorMessage =
                                result.message ?: "An unexpected error occurred"
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun removeContact(contact: Contact) {
        eventModel.value.event?.let {
            removeContactUseCase(it, contact)
                .filterNotNull()
                .onEach { result ->
                    when (result) {
                        is Operation.Success -> {
                            setContactsModel()
                        }

                        is Operation.Loading -> {
                            _contactsModel.value = ContactsModel(isLoading = true)
                        }

                        is Operation.Error -> {
                            _contactsModel.value = ContactsModel(
                                errorMessage =
                                result.message ?: "An unexpected error occurred"
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }


    fun handleNavigationEvent(event: EventPageNavigationEvent) {
        when(event) {
            is EventPageNavigationEvent.MoveBack -> navigateBack()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_MAIN_PAGE)
        }
    }

    private fun getAllContacts() {
        getAvailableContacts(eventModel.value.event?.userRemoteId ?: 0)
            .onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _availableContactsModel.value = ContactsModel(contacts = result.data ?: mutableListOf())
                    }
                    is Resource.Loading -> {
                        _availableContactsModel.value = ContactsModel(isLoading = true)
                    }
                    is Resource.Error -> {
                        _availableContactsModel.value = ContactsModel(errorMessage = result.message ?: "An unexpected error occurred")
                    }
                }
            }.launchIn(viewModelScope)
    }
}