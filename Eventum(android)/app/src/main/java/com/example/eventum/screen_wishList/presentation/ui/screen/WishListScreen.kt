package com.example.eventum.screen_wishList.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.eventum.screen_contacts.presentation.event.ContactsNavigationEvent
import com.example.eventum.screen_event.presentation.viewModel.EventViewModel
import com.example.eventum.screen_mainPage.presentation.ui.components.ScreenNavigator
import com.example.eventum.screen_wishList.presentation.event.WishListEvent
import com.example.eventum.screen_wishList.presentation.event.WishListNavigationEvent
import com.example.eventum.screen_wishList.presentation.ui.component.PresentItem
import com.example.eventum.screen_wishList.presentation.viewModel.WishListViewModel
import kotlinx.coroutines.launch
import androidx.compose.material3.TextButton as TextButton
import androidx.compose.foundation.lazy.items
import com.example.eventum.screen_presents.domain.model.Present

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishListScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: WishListViewModel = hiltViewModel()
) {
    val model = viewModel.model
    val snackbarHostState = remember { SnackbarHostState() }

    val navigationStatus by viewModel.navigationStatusRead.collectAsState()
    LaunchedEffect(navigationStatus) {
        try {
            navController.navigate(navigationStatus)
        } catch (_: Exception) {}
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var showAddDialog by remember { mutableStateOf(false) }
    var newTitle by remember { mutableStateOf("") }
    var newDescription by remember { mutableStateOf("") }

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
                        ?: Constants.NAVIGATION_MOVE_TO_WISHLIST_PAGE,
                    onNavigateToMainScreen = {
                        viewModel.handleNavigation(WishListNavigationEvent.NavigateToMainPage())
                    },
                    onNavigateToProfileScreen = {
                        viewModel.handleNavigation(WishListNavigationEvent.NavigateToProfilePage())
                    },
                    onNavigateToContactsScreen = {
                        viewModel.handleNavigation(WishListNavigationEvent.NavigateToContactsPage())
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
                        title = { Text("Мой вишлист") },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Меню")
                            }
                        },
                        actions = {
                            IconButton(onClick = { showAddDialog = true }) {
                                Icon(Icons.Default.Add, contentDescription = "Добавить подарок")
                            }
                        }
                    )
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    if (model.value.wishList?.presents.isNullOrEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Список подарков пуст", style = MaterialTheme.typography.bodyMedium)
                        }
                    } else {
                        LazyColumn {
                            items(model.value.wishList?.presents ?: mutableListOf()) { present ->
                                PresentItem(
                                    present = present,
                                    onEdit = {
                                        viewModel.handleEvent(WishListEvent.UpdatePresent(it))
                                    },
                                    onDelete = {
                                        viewModel.handleEvent(WishListEvent.DeletePresent(it))
                                    }
                                )
                            }
                        }
                    }
                }

                if (showAddDialog) {
                    AlertDialog(
                        onDismissRequest = { showAddDialog = false },
                        title = { Text("Добавить подарок") },
                        text = {
                            Column {
                                OutlinedTextField(
                                    value = newTitle,
                                    onValueChange = { newTitle = it },
                                    label = { Text("Название") }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = newDescription,
                                    onValueChange = { newDescription = it },
                                    label = { Text("Описание") }
                                )
                            }
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    if (newTitle.isNotBlank()) {
                                        viewModel.handleEvent(
                                            WishListEvent.CreatePresent(Present(
                                                title = newTitle,
                                                description = newDescription)
                                            )
                                        )
                                        showAddDialog = false
                                        newTitle = ""
                                        newDescription = ""
                                    }
                                }
                            ) {
                                Text("Создать")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showAddDialog = false }) {
                                Text(text = "Отмена")
                            }
                        }
                    )
                }
            }
        }
    )
}