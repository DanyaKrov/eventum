package com.example.eventum.app

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventum.R
import com.example.eventum.login.ui.screens.LoginScreen
import com.example.eventum.signUp.ui.screens.SignUpScreen
import com.example.eventum.util.StringRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
class EventumApplication : Application()

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "signUp") {
        composable("signUp") { SignUpScreen(navController) }
        composable("login") { LoginScreen(navController) }
    }
}