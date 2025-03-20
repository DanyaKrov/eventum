package com.example.eventum.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.example.eventum.app.HiltModule.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(
    context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        private val USER_ID_KEY = longPreferencesKey("user_id")
    }

    val userIdFlow: Flow<Long?> = dataStore.data
        .map { preferences ->
            preferences[USER_ID_KEY]
        }

    suspend fun saveUserId(userId: Long) {
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    suspend fun clearUserId() {
        dataStore.edit { preferences ->
            preferences.remove(USER_ID_KEY)
        }
    }
}