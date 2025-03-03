package com.example.eventum.app

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventum.screen_login.presentation.ui.screens.LoginScreen
import com.example.eventum.screen_mainPage.presentation.ui.screens.EventsScreen
import com.example.eventum.screen_signUp.presentation.ui.screens.SignUpScreen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EventumApplication : Application()

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "signUp") {
        composable("signUp") { SignUpScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("main_page") { EventsScreen(navController) }
    }
}