package com.example.eventum.screen_event.presentation.ui.screen

import android.app.DatePickerDialog
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.presentation.event.EventPageEvent
import com.example.eventum.screen_event.presentation.event.EventPageNavigationEvent
import com.example.eventum.screen_event.presentation.ui.component.NotificationItem
import com.example.eventum.screen_event.presentation.viewModel.EventViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EventScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: EventViewModel = hiltViewModel()
) {
    val eventModel = viewModel.eventModel.value
    val notificationsModel = viewModel.notificationsModel.value
    val context = LocalContext.current

    var showAddDialog by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }

    var editableTitle by remember { mutableStateOf("") }
    var editableDescription by remember { mutableStateOf("") }
    var editableDate by remember { mutableStateOf("") }

    var notificationTitle by remember { mutableStateOf("") }
    var notificationDate by remember { mutableStateOf("") }
    val calendar = remember { Calendar.getInstance() }

    val navigationStatus by viewModel.navigationStatus.collectAsState()
    LaunchedEffect(navigationStatus) {
        try {
            navController.navigate(navigationStatus)
        } catch (_: Exception) {}
    }

    LaunchedEffect(isEditing) {
        if (isEditing) {
            eventModel.event?.let {
                editableTitle = it.name
                editableDescription = it.description
                editableDate = it.time
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали события") },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.handleNavigationEvent(EventPageNavigationEvent.MoveBack())
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        isEditing = true
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Редактировать")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Добавить уведомление")
            }
        }
    ) { padding ->
        if (eventModel.uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding), contentAlignment = Alignment.Center
            ) {
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                eventModel.event?.let { event ->
                    if (isEditing) {
                        OutlinedTextField(
                            value = editableTitle,
                            onValueChange = { editableTitle = it },
                            label = { Text("Название") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = editableDescription,
                            onValueChange = { editableDescription = it },
                            label = { Text("Описание") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = editableDate,
                            onValueChange = {},
                            label = { Text("Дата (yyyy-MM-dd)") },
                            readOnly = true,
                            trailingIcon = {
                                IconButton(onClick = {
                                    DatePickerDialog(
                                        context,
                                        { _, year, month, dayOfMonth ->
                                            calendar.set(year, month, dayOfMonth)
                                            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                            editableDate = format.format(calendar.time)
                                        },
                                        calendar.get(Calendar.YEAR),
                                        calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.DAY_OF_MONTH)
                                    ).show()
                                }) {
                                    Icon(Icons.Default.CalendarToday, contentDescription = null)
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            viewModel.handleEvent(EventPageEvent.EditEvent(
                                event.copy(
                                    name = editableTitle,
                                    description = editableDescription,
                                    time = editableDate
                                )
                            ))
                            isEditing = false
                        }) {
                            Text("Сохранить")
                        }
                    } else {
                        Text(text = "Название: ${event.name}", style = MaterialTheme.typography.titleLarge)
                        Text(text = "Описание: ${event.description}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Дата: ${event.time}", style = MaterialTheme.typography.bodyMedium)
                        event.tag?.let {
                            Text(text = "Тэг: $it", style = MaterialTheme.typography.bodySmall)
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text("Уведомления:", style = MaterialTheme.typography.titleMedium)

                        LazyColumn {
                            items(notificationsModel.notifications) { notification ->
                                NotificationItem(
                                    notification = notification,
                                    onEdit = { },
                                    onDelete = {
                                        viewModel.handleEvent(EventPageEvent.DeleteNotification(it))
                                    }
                                )
                                Divider()
                            }
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

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    if (notificationTitle.isNotBlank() && notificationDate.isNotBlank()) {
                        viewModel.handleEvent(
                            EventPageEvent.CreateNotification(
                                NotificationModel(
                                    title = notificationTitle,
                                    description = "",
                                    time = notificationDate,
                                    eventOwnerId = eventModel.event?.remoteId ?: 0
                                )
                            )
                        )
                        notificationTitle = ""
                        notificationDate = ""
                        showAddDialog = false
                    }
                }) {
                    Text("Добавить")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Отмена")
                }
            },
            title = { Text("Новое уведомление") },
            text = {
                Column {
                    OutlinedTextField(
                        value = notificationTitle,
                        onValueChange = { notificationTitle = it },
                        label = { Text("Заголовок") }
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = notificationDate,
                        onValueChange = {},
                        label = { Text("Дата (yyyy-MM-dd)") },
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = {
                                DatePickerDialog(
                                    context,
                                    { _, year, month, dayOfMonth ->
                                        calendar.set(year, month, dayOfMonth)
                                        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                        notificationDate = format.format(calendar.time)
                                    },
                                    calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),
                                    calendar.get(Calendar.DAY_OF_MONTH)
                                ).show()
                            }) {
                                Icon(Icons.Default.CalendarToday, contentDescription = null)
                            }
                        }
                    )
                }
            }
        )
    }
}
