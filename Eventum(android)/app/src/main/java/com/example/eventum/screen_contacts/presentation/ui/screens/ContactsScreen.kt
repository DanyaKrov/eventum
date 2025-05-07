package com.example.eventum.screen_contacts.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.eventum.screen_contacts.domain.model.ContactRequestModel
import com.example.eventum.screen_contacts.presentation.event.ContactsEvent
import com.example.eventum.screen_contacts.presentation.ui.components.ContactItem
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.SoftLightOrange
import com.example.eventum.ui.theme.SoftOrange
import com.example.eventum.ui.theme.SoftRed

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
    var newLogin by remember { mutableStateOf("") }


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
                    onNavigateToWishListScreen = {
                        viewModel.handleNavigation(ContactsNavigationEvent.NavigateToWishListPage())
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
                        LazyColumn(Modifier.weight(1f)) {
                            items(model.value.contacts) { contact ->
                                ContactItem(
                                    contact = contact,
                                    onEdit = {
                                        viewModel.handleEvent(ContactsEvent.EditContactEvent(it))
                                    },
                                    onDelete = {
                                        viewModel.handleEvent(ContactsEvent.DeleteContactEvent(it))
                                    },
                                    onExpand = {
                                        viewModel.handleNavigation(ContactsNavigationEvent.NavigateToContactGiftsPage(it))
                                    }
                                )
                                Spacer(Modifier.height(8.dp))
//                                Divider()
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(
                        onClick = {
                            showAddDialog = true
                        },
                        modifier = Modifier.fillMaxWidth()
                            .background(
                                Brush.horizontalGradient(
                                colors = listOf(
                                    SoftOrange,
                                    SoftLightOrange
                                )),
                                shape = MaterialTheme.shapes.extraLarge),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)

                    ) {
                        Text("Добавить контакт",
                            color = Color.DarkGray)
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
                        title = { Text("Создать контакт",
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray) },
                        text = {
                            Column(Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    value = newName,
                                    onValueChange = { newName = it },
                                    label = { Text("Имя контакта") },
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(40.dp),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                        focusedLabelColor = Color.Gray,
                                        unfocusedLabelColor = Color.LightGray
                                    )

                                )
                                OutlinedTextField(
                                    value = newLogin,
                                    onValueChange = { newLogin = it },
                                    label = { Text("Login (не обязательно)") },
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(40.dp),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedBorderColor = Color.Gray,
                                        unfocusedBorderColor = Color.LightGray,
                                        focusedLabelColor = Color.Gray,
                                        unfocusedLabelColor = Color.LightGray
                                    )
                                )
                            }

                        },
                        confirmButton = {
                            TextButton(onClick = {
                                if (newName.isNotBlank()) {
                                    viewModel.handleEvent(
                                        ContactsEvent.AddContactEvent(
                                            ContactRequestModel(
                                                name = newName,
                                                authorisedLogin = newLogin.takeIf { it != "" }
                                            )
                                        )
                                    )
                                    newName = ""
                                    showAddDialog = false
                                    newLogin = ""
                                }
                            }) {
                                Text("Создать",
                                    fontFamily = Montserrat,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.DarkGray)
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                showAddDialog = false
                                newName = ""
                                newLogin = ""
                            }) {
                                Text("Отмена",
                                    fontFamily = Montserrat,
                                    fontWeight = FontWeight.Medium,
                                    color = SoftRed)
                            }
                        }
                    )
                }
            }
        }
    )
}