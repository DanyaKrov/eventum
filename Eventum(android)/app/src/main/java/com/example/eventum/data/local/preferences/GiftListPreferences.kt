package com.example.eventum.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.example.eventum.app.HiltModule.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContactGiftListPreferences @Inject constructor(
    context: Context
) {
    private val dataStore = context.dataStore
    companion object {
        private val CONTACT_ID_KEY = longPreferencesKey("contact_id")
    }

    val contactId: Flow<Long?> = dataStore.data
        .map { preferences ->
            preferences[CONTACT_ID_KEY]
        }


    suspend fun saveContactId(id: Long) {
        dataStore.edit { preferences -> preferences[CONTACT_ID_KEY] = id }
    }

    suspend fun clearContactId() {
        dataStore.edit { preferences -> preferences.remove(CONTACT_ID_KEY) }
    }

}