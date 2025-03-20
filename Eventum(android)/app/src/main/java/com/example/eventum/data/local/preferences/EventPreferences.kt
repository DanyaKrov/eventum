package com.example.eventum.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.example.eventum.app.HiltModule.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventPreferences @Inject constructor(
    context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        private val EVENT_ID_KEY = longPreferencesKey("event_id")
    }

    val eventIdFlow: Flow<Long?> = dataStore.data
        .map { preferences ->
            preferences[EVENT_ID_KEY]
        }

    suspend fun getEventId(): Long {
        return dataStore.data.firstOrNull()?.get(EVENT_ID_KEY) ?: 0
    }

    suspend fun saveEventId(eventId: Long) {
        dataStore.edit { preferences ->
            preferences[EVENT_ID_KEY] = eventId
        }
    }

    suspend fun clearEventId() {
        dataStore.edit { preferences ->
            preferences.remove(EVENT_ID_KEY)
        }
    }
}