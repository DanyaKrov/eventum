package com.example.eventum.mainPage.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.api.model.UserResponse
import com.example.eventum.mainPage.api.EventsRepository
import com.example.eventum.mainPage.event.MainPageEvent
import com.example.eventum.mainPage.event.NavigationEvent
import com.example.eventum.mainPage.model.Event
import com.example.eventum.roomDatabase.app.AppDatabase
import com.example.eventum.roomDatabase.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val roomRepository: UserRepository,
    private val repository: EventsRepository
): ViewModel() {
    var events: MutableList<Event> = mutableListOf()
    init {
        viewModelScope.launch {
            // repository.getEvents(user.id)
        }
    }

    fun handleEvent(event: MainPageEvent) {
        when(event){
            is MainPageEvent.EventDeleted -> TODO()
            is MainPageEvent.EventEdit -> TODO()
            is MainPageEvent.EventExpanded -> TODO()
        }
    }



    fun handleNavigation(event: NavigationEvent) {
        when(event){
            is NavigationEvent.ChangeToCalendarView -> TODO()
            is NavigationEvent.NavigateToPreparationsPage -> TODO()
            is NavigationEvent.NavigateToProfilePage -> TODO()
            is NavigationEvent.NavigateToSettings -> TODO()
        }
    }

}