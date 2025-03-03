package com.example.eventum.screen_presents.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventum.screen_mainPage.presentation.viewModel.MainPageViewModel
import com.example.eventum.screen_presents.presentation.viewModel.PresentsViewModel

@Composable
@Preview
fun PresentsScreen(navController: NavHostController = rememberNavController(),
                 viewModel: PresentsViewModel = hiltViewModel()
) {
}