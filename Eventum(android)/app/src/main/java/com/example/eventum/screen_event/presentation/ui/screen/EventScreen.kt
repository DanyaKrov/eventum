package com.example.eventum.screen_event.presentation.ui.screen

import android.app.DatePickerDialog
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.presentation.event.EventPageEvent
import com.example.eventum.screen_event.presentation.event.EventPageNavigationEvent
import com.example.eventum.screen_event.presentation.ui.component.EventContactsSection
import com.example.eventum.screen_event.presentation.ui.component.NotificationItem
import com.example.eventum.screen_event.presentation.viewModel.EventViewModel
import com.example.eventum.screen_hello.presentation.ui.components.BackButton
import com.example.eventum.ui.theme.BackGround
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.SoftLightOrange
import com.example.eventum.ui.theme.SoftLightRed
import com.example.eventum.ui.theme.SoftOrange
import com.example.eventum.ui.theme.SoftRed
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
    val contactsModel = viewModel.contactsModel.value
    val availableContactsModel = viewModel.availableContactsModel.value

    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }
    val rounding = 40.dp

    var showAddDialog by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var editableTitle by remember { mutableStateOf("") }
    var editableDescription by remember { mutableStateOf("") }
    var editableDate by remember { mutableStateOf("") }
    var notificationTitle by remember { mutableStateOf("") }
    var notificationDate by remember { mutableStateOf("") }

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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.handleNavigationEvent(EventPageNavigationEvent.MoveBack())
                },
                containerColor = Color.White
            ) {
                Icon(Icons.Sharp.ArrowBack, contentDescription = "Назад")
            }
        }
    ) { padding ->
        if (eventModel.uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {}
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                eventModel.event?.let { event ->
                    if (isEditing) {
                        Text("Редактирование события", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(8.dp))

                        OutlinedTextField(
                            value = editableTitle,
                            onValueChange = { editableTitle = it },
                            label = { Text("Название") },
                            shape = RoundedCornerShape(rounding),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(8.dp))
                        OutlinedTextField(
                            value = editableDescription,
                            onValueChange = { editableDescription = it },
                            label = { Text("Описание") },
                            shape = RoundedCornerShape(rounding),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(8.dp))
                        OutlinedTextField(
                            value = editableDate,
                            onValueChange = {},
                            label = { Text("Дата") },
                            readOnly = true,
                            trailingIcon = {
                                IconButton(onClick = {
                                    DatePickerDialog(
                                        context,
                                        { _, y, m, d ->
                                            calendar.set(y, m, d)
                                            editableDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                                .format(calendar.time)
                                        },
                                        calendar.get(Calendar.YEAR),
                                        calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.DAY_OF_MONTH)
                                    ).show()
                                }) {
                                    Icon(Icons.Default.CalendarToday, null)
                                }
                            },
                            shape = RoundedCornerShape(rounding),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = {
                                viewModel.handleEvent(EventPageEvent.EditEvent(event.copy(
                                    name = editableTitle,
                                    description = editableDescription,
                                    time = editableDate
                                )))
                                isEditing = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Сохранить")
                        }
                    } else {
                        Text("Название: ${event.name}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
                        Text("Описание: ${event.description}")
                        Text("Дата: ${event.time}")
                        Spacer(Modifier.height(12.dp))

                        Button(onClick = { isEditing = true }, modifier = Modifier.fillMaxWidth()) {
                            Text("Редактировать")
                        }

                        Spacer(Modifier.height(20.dp))

                        SelectContactScreen(
                            allContacts = availableContactsModel.contacts,
                            attachedContacts = contactsModel.contacts ?: emptyList(),
                            onAttachContacts = { contacts ->
                                contacts.forEach {
                                    viewModel.handleEvent(EventPageEvent.AddContact(it))
                                }
                            }
                        )

                        Spacer(Modifier.height(20.dp))

                        Text("Уведомления", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                        Spacer(Modifier.height(8.dp))

                        LazyColumn {
                            items(notificationsModel.notifications) { notification ->
                                NotificationItem(
                                    notification = notification,
                                    onEdit = { viewModel.handleEvent(EventPageEvent.EditNotification(it)) },
                                    onDelete = { viewModel.handleEvent(EventPageEvent.DeleteNotification(it)) }
                                )
                                Spacer(Modifier.height(8.dp))
                            }

                            item {
                                Spacer(Modifier.height(8.dp))
                                Button(
                                    onClick = { showAddDialog = true },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Добавить напоминание")
                                }
                            }
                        }
                    }
                }

                if (eventModel.uiState.errorMessage?.isNotBlank() == true) {
                    Spacer(Modifier.height(16.dp))
                    eventModel.uiState.errorMessage?.let { Text(it, color = Color.Red) }
                }
            }
        }
    }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Новое уведомление") },
            text = {
                Column {
                    OutlinedTextField(
                        value = notificationTitle,
                        onValueChange = { notificationTitle = it },
                        label = { Text("Заголовок") },
                        shape = RoundedCornerShape(rounding)
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = notificationDate,
                        onValueChange = {},
                        label = { Text("Дата") },
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = {
                                DatePickerDialog(
                                    context,
                                    { _, y, m, d ->
                                        calendar.set(y, m, d)
                                        notificationDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                            .format(calendar.time)
                                    },
                                    calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),
                                    calendar.get(Calendar.DAY_OF_MONTH)
                                ).show()
                            }) {
                                Icon(Icons.Default.CalendarToday, null)
                            }
                        },
                        shape = RoundedCornerShape(rounding)
                    )
                }
            },
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
            }
        )
    }
}
