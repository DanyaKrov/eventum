package com.example.eventum.mainPage.event

sealed class MainPageEvent {
    class EventExpanded(): MainPageEvent()
    class EventDeleted(): MainPageEvent()
    class EventEdit(): MainPageEvent()
}