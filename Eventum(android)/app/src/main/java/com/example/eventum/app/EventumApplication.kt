package com.example.eventum.app

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventum.common.Constants
import com.example.eventum.screen_contacts.presentation.ui.screens.ContactsScreen
import com.example.eventum.screen_event.presentation.ui.screen.EventScreen
import com.example.eventum.screen_hello.presentation.ui.screens.HelloScreen
import com.example.eventum.screen_initial.presentation.ui.InitialScreen
import com.example.eventum.screen_login.presentation.ui.screens.LoginScreen
import com.example.eventum.screen_mainPage.presentation.ui.screens.EventsScreen
import com.example.eventum.screen_profile.presentation.ui.screen.ProfileScreen
import com.example.eventum.screen_signUp.presentation.ui.screens.SignUpScreen
import com.example.eventum.screen_wishList.presentation.ui.screen.WishListScreen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EventumApplication : Application()

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Constants.NAVIGATION_MOVE_TO_INITIAL_PAGE) {
        composable(Constants.NAVIGATION_MOVE_TO_SIGNUP_PAGE) { SignUpScreen(navController) }
        composable(Constants.NAVIGATION_MOVE_TO_LOGIN_PAGE) { LoginScreen(navController) }
        composable(Constants.NAVIGATION_MOVE_TO_MAIN_PAGE) { EventsScreen(navController) }
        composable(Constants.NAVIGATION_MOVE_TO_INITIAL_PAGE) { InitialScreen(navController) }
        composable(Constants.NAVIGATION_MOVE_TO_EVENT_PAGE) { EventScreen(navController) }
        composable(Constants.NAVIGATION_MOVE_TO_HELLO_PAGE) { HelloScreen(navController) }
        composable(Constants.NAVIGATION_MOVE_TO_CONTACTS_PAGE) { ContactsScreen(navController) }
        composable(Constants.NAVIGATION_MOVE_TO_WISHLIST_PAGE) { WishListScreen(navController) }
        composable(Constants.NAVIGATION_MOVE_TO_PROFILE_PAGE) { ProfileScreen(navController) }

    }
}