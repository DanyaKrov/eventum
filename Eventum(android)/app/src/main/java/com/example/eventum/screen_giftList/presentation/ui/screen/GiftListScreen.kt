package com.example.eventum.screen_giftList.presentation.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventum.screen_event.presentation.viewModel.EventViewModel
import com.example.eventum.screen_giftList.presentation.viewModel.GiftListViewModel
import java.util.Calendar

@Composable
fun GiftListScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: GiftListViewModel = hiltViewModel()
) {

    val navigationStatus by viewModel.navigationStatusRead.collectAsState()
    LaunchedEffect(navigationStatus) {
        try {
            navController.navigate(navigationStatus)
        } catch (_: Exception) {
        }
    }
}