package com.example.eventum.screen_contacts.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventum.common.Constants
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.presentation.event.ContactsNavigationEvent
import com.example.eventum.screen_contacts.presentation.viewModel.ContactsViewModel
import com.example.eventum.screen_mainPage.presentation.event.MainPageNavigationEvent
import com.example.eventum.screen_mainPage.presentation.ui.components.ScreenNavigator
import com.example.eventum.screen_mainPage.presentation.viewModel.MainPageViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import com.example.eventum.screen_contacts.presentation.event.ContactsEvent
import com.example.eventum.screen_contacts.presentation.ui.components.ContactItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: ContactsViewModel = hiltViewModel()
) {
    val model = viewModel.model
    val snackbarHostState = remember { SnackbarHostState() }

    val navigationStatus by viewModel.navigationStatusRead.collectAsState()
    LaunchedEffect(navigationStatus) {
        try {
            navController.navigate(navigationStatus)
        } catch (_: Exception) {
        }
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var showAddDialog by remember { mutableStateOf(false) }
    var newName by remember { mutableStateOf("") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(280.dp),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                ScreenNavigator(
                    currentScreen = navController.currentDestination?.route
                        ?: Constants.NAVIGATION_MOVE_TO_CONTACTS_PAGE,
                    onNavigateToMainScreen = {
                        viewModel.handleNavigation(ContactsNavigationEvent.NavigateToMainPage())
                    },
                    onNavigateToProfileScreen = {
                        viewModel.handleNavigation(ContactsNavigationEvent.NavigateToProfilePage())
                    },
                    onCloseNavigator = { scope.launch { drawerState.close() } }
                )
            }
        },
        content = {
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                topBar = {
                    SmallTopAppBar(
                        title = { Text("Контакты") },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Меню")
                            }
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { showAddDialog = true }) {
                        Text("+")
                    }
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    if (model.value.isLoading) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            // show something while loading main data
                        }
                    } else if (model.value.contacts.isEmpty()) {
                        Text("Список контактов пуст", style = MaterialTheme.typography.bodyMedium)
                    } else {
                        LazyColumn {
                            items(model.value.contacts) { contact ->
                                ContactItem(
                                    contact = contact,
                                    onEdit = {
                                        viewModel.handleEvent(ContactsEvent.EditContactEvent(it))
                                    }
                                )
                                Divider()
                            }
                        }
                    }

                    if (model.value.errorMessage.isNotBlank()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = model.value.errorMessage, color = Color.Red)
                    }
                }

                // start of adding new contact
                if (showAddDialog) {
                    AlertDialog(
                        onDismissRequest = { showAddDialog = false },
                        title = { Text("Создать контакт") },
                        text = {
                            OutlinedTextField(
                                value = newName,
                                onValueChange = { newName = it },
                                label = { Text("Имя контакта") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                if (newName.isNotBlank()) {
                                    viewModel.handleEvent(
                                        ContactsEvent.AddContactEvent(
                                            Contact(
                                                name = newName,
                                                userRemoteId = 0
                                            )
                                        )
                                    )
                                    newName = ""
                                    showAddDialog = false
                                }
                            }) {
                                Text("Создать")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                showAddDialog = false
                                newName = ""
                            }) {
                                Text("Отмена")
                            }
                        }
                    )
                }
            }
        }
    )
}