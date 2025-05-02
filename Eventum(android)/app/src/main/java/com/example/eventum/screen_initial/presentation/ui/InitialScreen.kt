package com.example.eventum.screen_initial.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventum.screen_initial.presentation.viewModel.InitialViewModel
import com.example.eventum.screen_signUp.presentation.viewModel.SignUpViewModel

@Composable
fun InitialScreen(navController: NavHostController = rememberNavController(),
                  initialViewModel: InitialViewModel = hiltViewModel()
) {
    val navigationStatus by initialViewModel.navigationStatusRead.collectAsState()
    LaunchedEffect(navigationStatus) {
        try {
            navController.navigate(navigationStatus)
        } catch (_: Exception) {
        }
    }
}