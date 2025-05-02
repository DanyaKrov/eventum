package com.example.eventum.screen_mainPage.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventum.screen_login.presentation.viewModel.LoginViewModel
import com.example.eventum.screen_mainPage.presentation.event.MainPageEvent
import com.example.eventum.screen_mainPage.presentation.ui.components.CreateEvent
import com.example.eventum.screen_mainPage.presentation.ui.components.EventItem
import com.example.eventum.screen_mainPage.presentation.viewModel.MainPageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(navController: NavHostController = rememberNavController(),
                 viewModel: MainPageViewModel = hiltViewModel())
{
    val model = viewModel.model
    val snackbarHostState = remember { SnackbarHostState() }

    val navigationStatus by viewModel.navigationStatusRead.collectAsState()
    LaunchedEffect(navigationStatus) {
        try {
            navController.navigate(navigationStatus)
        } catch (_: Exception) {
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            item {
                CreateEvent(
                    context = LocalContext.current,
                    onCreate = { viewModel.handleEvent(MainPageEvent.EventCreate(it)) },
                    creationStatus = viewModel.eventCreationStatus,
                    snackbarHostState = snackbarHostState
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            items(model.value.events) { event ->
                EventItem(event = event,
                    onLongClick =
                    { viewModel.handleEvent(MainPageEvent.EventDelete(it))},
                    onShortClick =
                    { viewModel.handleEvent(MainPageEvent.EventExpanded(it))})
            }
        }
    }
}