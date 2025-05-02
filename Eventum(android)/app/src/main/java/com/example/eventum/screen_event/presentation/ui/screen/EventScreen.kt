package com.example.eventum.screen_event.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.eventum.screen_event.domain.model.EventModel
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.presentation.ui.component.NotificationItem
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventum.screen_event.presentation.event.EventPageEvent
import com.example.eventum.screen_event.presentation.event.EventPageNavigationEvent
import com.example.eventum.screen_event.presentation.viewModel.EventViewModel
import com.example.eventum.screen_mainPage.presentation.event.MainPageEvent
import com.example.eventum.screen_mainPage.presentation.viewModel.MainPageViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EventScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: EventViewModel = hiltViewModel()
) {
    val eventModel = viewModel.eventModel.value
    val notificationsModel = viewModel.notificationsModel.value
    val context = LocalContext.current

    val navigationStatus by viewModel.navigationStatus.collectAsState()
    LaunchedEffect(navigationStatus) {
        try {
            navController.navigate(navigationStatus)
        } catch (_: Exception) {
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали события") },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.handleNavigationEvent(EventPageNavigationEvent.MoveBack()) }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.handleNavigationEvent(EventPageNavigationEvent.MoveBack())
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Редактировать")
                    }
                }
            )
        }
    ) { padding ->
        if (eventModel.uiState.isLoading) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(padding), contentAlignment = Alignment.Center) {
            }
        } else {
            Column(modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
            ) {
                eventModel.event?.let { event ->
                    Text(text = "Название: ${event.name}", style = MaterialTheme.typography.titleLarge)
                    Text(text = "Описание: ${event.description}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Дата: ${event.time}", style = MaterialTheme.typography.bodyMedium)
                    event.tag?.let { Text(text = "Тэг: $it", style = MaterialTheme.typography.bodySmall) }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text("Уведомления:", style = MaterialTheme.typography.titleMedium)

                    LazyColumn {
                        items(notificationsModel.notifications) { notification ->
                            NotificationItem(
                                notification = notification,
                                onEdit = {  },
                                onDelete = {  }
                            )
                            Divider()
                        }
                    }
                }

                if (!eventModel.uiState.errorMessage.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = eventModel.uiState.errorMessage, color = Color.Red)
                }
            }
        }
    }
}