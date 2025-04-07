package com.example.eventum.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.example.eventum.app.HiltModule.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GiftListPreferences @Inject constructor(
    context: Context
) {
    private val dataStore = context.dataStore
    companion object {
        private val GIFTLIST_ID_KEY = longPreferencesKey("giftlist_id")
    }

    val giftListIdFlow: Flow<Long?> = dataStore.data
        .map { preferences ->
            preferences[GIFTLIST_ID_KEY]
        }


    suspend fun saveGiftListId(id: Long) {
        dataStore.edit { preferences -> preferences[GIFTLIST_ID_KEY] = id }
    }

    suspend fun clearGiftListId() {
        dataStore.edit { preferences -> preferences.remove(GIFTLIST_ID_KEY) }
    }

}