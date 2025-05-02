package com.example.eventum.screen_mainPage.domain.useCase

import com.example.eventum.data.local.preferences.EventPreferences
import com.example.eventum.screen_mainPage.domain.model.Event
import javax.inject.Inject

class SelectEventUseCase @Inject constructor(
    private val eventPreferences: EventPreferences
) {
    suspend operator fun invoke(event: Event) {
        eventPreferences.saveEventId(event.remoteId)
    }
}