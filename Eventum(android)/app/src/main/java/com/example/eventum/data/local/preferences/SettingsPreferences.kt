package com.example.eventum.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.eventum.app.HiltModule.dataStore
import com.example.eventum.data.local.preferences.model.WishListVisibility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsPreferences @Inject constructor(
    context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        private val THEME_KEY = booleanPreferencesKey("dark_mode")
        private val WISHLIST_VISIBILITY_KEY = stringPreferencesKey("wishList_visibility")
    }


    val darkModeFlow: Flow<Boolean> = dataStore.data
        .map { preferences -> preferences[THEME_KEY] ?: false }

    val wishListVisibilityFLow: Flow<WishListVisibility> = dataStore.data
        .map { preferences ->
           WishListVisibility.fromString(preferences[WISHLIST_VISIBILITY_KEY] ?: "FRIENDS") }

    suspend fun saveDarkMode(enabled: Boolean) {
        dataStore.edit { preferences -> preferences[THEME_KEY] = enabled }
    }

    suspend fun saveWishListVisibility(visibility: String) {
        dataStore.edit { preferences -> preferences[WISHLIST_VISIBILITY_KEY] = visibility }
    }
}