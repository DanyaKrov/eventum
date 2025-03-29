package com.example.eventum.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.preferencesOf
import com.example.eventum.app.HiltModule.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WishListPreferences @Inject constructor(
    context: Context
) {
    private val dataStore = context.dataStore
    companion object {
        private val WISHLIST_ID_KEY = longPreferencesKey("wishlist_id")
    }

    val wishListIdFlow: Flow<Long?> = dataStore.data
        .map { preferences ->
            preferences[WISHLIST_ID_KEY]
        }


    suspend fun saveWishListId(id: Long) {
        dataStore.edit { preferences -> preferences[WISHLIST_ID_KEY] = id }
    }

    suspend fun clearWishListId() {
        dataStore.edit { preferences -> preferences.remove(WISHLIST_ID_KEY) }
    }

}