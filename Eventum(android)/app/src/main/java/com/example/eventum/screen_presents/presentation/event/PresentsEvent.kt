package com.example.eventum.screen_presents.presentation.event

import com.example.eventum.screen_presents.domain.model.Present

sealed class PresentsEvent {
    class EditPresentEvent(val present: Present): PresentsEvent()
    class AddPresentEvent(): PresentsEvent()
    class SortPresentsEvent(): PresentsEvent()
}