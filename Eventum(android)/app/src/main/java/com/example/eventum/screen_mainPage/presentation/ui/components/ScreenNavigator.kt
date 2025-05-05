package com.example.eventum.screen_mainPage.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eventum.common.Constants

@Composable
fun ScreenNavigator(
    currentScreen: String,
    onNavigateToMainScreen: () -> Unit = {},
    onNavigateToContactsScreen: () -> Unit = {},
    onNavigateToProfileScreen: () -> Unit = {},
    onNavigateToWishListScreen: () -> Unit = {},
    onCloseNavigator: () -> Unit = {}
) {
    Column(modifier = Modifier.padding(16.dp)) {
        NavigationItem(
            title = "События",
            isSelected = currentScreen == Constants.NAVIGATION_MOVE_TO_MAIN_PAGE,
            onClick = {
                if (currentScreen != Constants.NAVIGATION_MOVE_TO_MAIN_PAGE) {
                    onNavigateToMainScreen()
                    onCloseNavigator()
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        NavigationItem(
            title = "Контакты",
            isSelected = currentScreen == Constants.NAVIGATION_MOVE_TO_CONTACTS_PAGE,
            onClick = {
                if (currentScreen != Constants.NAVIGATION_MOVE_TO_CONTACTS_PAGE) {
                    onNavigateToContactsScreen()
                    onCloseNavigator()
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        NavigationItem(
            title = "Профиль",
            isSelected = currentScreen == Constants.NAVIGATION_MOVE_TO_PROFILE_PAGE,
            onClick = {
                if (currentScreen != Constants.NAVIGATION_MOVE_TO_PROFILE_PAGE) {
                    onNavigateToProfileScreen()
                    onCloseNavigator()
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        NavigationItem(
            title = "Вишлист",
            isSelected = currentScreen == Constants.NAVIGATION_MOVE_TO_WISHLIST_PAGE,
            onClick = {
                if (currentScreen != Constants.NAVIGATION_MOVE_TO_WISHLIST_PAGE) {
                    onNavigateToWishListScreen()
                    onCloseNavigator()
                }
            }
        )
    }
}

@Composable
fun NavigationItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    androidx.compose.material3.TextButton(
        onClick = onClick,
        enabled = !isSelected
    ) {
        androidx.compose.material3.Text(
            text = title,
            color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            else MaterialTheme.colorScheme.onSurface
        )
    }
}