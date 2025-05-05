package com.example.eventum.screen_contacts.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.common.Constants
import com.example.eventum.domain.model.Resource
import com.example.eventum.data.local.preferences.UserPreferences
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.domain.model.ContactsModel
import com.example.eventum.screen_contacts.domain.useCase.AddContactUseCase
import com.example.eventum.screen_contacts.domain.useCase.DeleteContactUseCase
import com.example.eventum.screen_contacts.domain.useCase.GetContactsUseCase
import com.example.eventum.screen_contacts.domain.useCase.UpdateContactUseCase
import com.example.eventum.screen_contacts.presentation.event.ContactsEvent
import com.example.eventum.screen_contacts.presentation.event.ContactsNavigationEvent
import com.example.eventum.screen_contacts.presentation.sort.SortOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
    private val updateContactUseCase: UpdateContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase,
    private val addContactUseCase: AddContactUseCase,
    private val userPreferences: UserPreferences
): ViewModel() {
    // navigation parameters
    private val navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatusRead: StateFlow<String> = navigationStatus

    private val _model = mutableStateOf(ContactsModel()) // mutable state of model
    val model: State<ContactsModel> = _model // immutable state of model to presentation layer

    private var userId: Long = 0

    private val _sortOrder = MutableStateFlow(SortOrder.DATE_ASC)
    val sortOrder: StateFlow<SortOrder> = _sortOrder.asStateFlow()
    
    init {
        getContacts()
    }

    private fun setUserId(id: Long) {
        userId = id
    }

    private fun getContacts() {
        userPreferences.userIdFlow // state of user Id
            .onEach { userId ->
                if (userId == null) // it means no userId presented at the moment
                    navigationStatus.emit(Constants.NAVIGATION_MOVE_TO_LOGIN_PAGE)
            }
            .filterNotNull()
            .flatMapLatest { userId ->
                setUserId(userId)
                getContactsUseCase(userId) // if id changes, contacts update
            }
            .onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _model.value = ContactsModel(contacts = result.data ?: mutableListOf())
                    }
                    is Resource.Loading -> {
                        _model.value = ContactsModel(isLoading = true)
                    }
                    is Resource.Error -> {
                        _model.value = ContactsModel(errorMessage = result.message ?: "An unexpected error occurred")
                    }
                }
            }
            .launchIn(viewModelScope)
    }


    private fun editContact(contact: Contact) {
        viewModelScope.launch {
            updateContactUseCase(contact)
        }
    }


    private fun deleteContact(contactId: Long) {
        viewModelScope.launch {
            deleteContactUseCase(contactId)
        }
    }


    private fun changeSortOrder(order: SortOrder) {
        _sortOrder.value = order
        _model.value.contacts = sortContacts(model.value.contacts)
        // sorting ui elements
    }

    private fun sortContacts(contacts: MutableList<Contact>): MutableList<Contact> {
        return when (sortOrder.value) {
            SortOrder.DATE_ASC -> contacts.sortedBy { it.id }
            SortOrder.DATE_DESC -> contacts.sortedByDescending { it.id }
            SortOrder.NAME_ASC -> contacts.sortedBy { it.name }
            SortOrder.NAME_DESC -> contacts.sortedByDescending { it.name }
        }.toMutableList()
    }

    private fun sortContactsByTag(tag: String) {
        _model.value.contacts = model.value.contacts.filter { it.tag == tag }.toMutableList()
    }

    fun handleEvent(event: ContactsEvent) {
        when(event) {
            is ContactsEvent.EditContactEvent -> editContact(event.contact)
            is ContactsEvent.SortContactsEvent -> changeSortOrder(event.order)
            is ContactsEvent.SortTagContactsEvent -> sortContactsByTag(event.tag)
            is ContactsEvent.AddContactEvent -> addContact(event.contact)
            is ContactsEvent.DeleteContactEvent -> deleteContact(event.contact.remoteId)
        }
    }

    private fun addContact(contact: Contact) {
        viewModelScope.launch {
            addContactUseCase(userId, contact)
        }
    }


    fun handleNavigation(event: ContactsNavigationEvent) {
        when(event) {
            is ContactsNavigationEvent.ChangeToCalendarView -> TODO()
            is ContactsNavigationEvent.NavigateToMainPage -> TODO()
            is ContactsNavigationEvent.NavigateToProfilePage -> TODO()
        }
    }
}