package com.example.eventum.screen_mainPage.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventum.common.Constants
import com.example.eventum.screen_login.presentation.viewModel.LoginViewModel
import com.example.eventum.screen_mainPage.presentation.event.MainPageEvent
import com.example.eventum.screen_mainPage.presentation.event.MainPageNavigationEvent
import com.example.eventum.screen_mainPage.presentation.ui.components.CreateEvent
import com.example.eventum.screen_mainPage.presentation.ui.components.EventItem
import com.example.eventum.screen_mainPage.presentation.ui.components.ScreenNavigator
import com.example.eventum.screen_mainPage.presentation.viewModel.MainPageViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: MainPageViewModel = hiltViewModel()
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
                        ?: Constants.NAVIGATION_MOVE_TO_MAIN_PAGE,
                    onNavigateToContactsScreen = {
                        viewModel.handleNavigation(MainPageNavigationEvent.NavigateToContactsPage())
                    },
                    onNavigateToProfileScreen = {
                        viewModel.handleNavigation(MainPageNavigationEvent.NavigateToProfilePage())
                    },
                    onNavigateToWishListScreen = {
                        viewModel.handleNavigation(MainPageNavigationEvent.NavigateToWishListPage())
                    },
                    onCloseNavigator = { scope.launch { drawerState.close() } }
                )
            }
        }
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                androidx.compose.material3.TopAppBar(
                    title = { androidx.compose.material3.Text("События") },
                    navigationIcon = {
                        androidx.compose.material3.IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            androidx.compose.material3.Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Menu,
                                contentDescription = "Открыть меню"
                            )
                        }
                    }
                )
            }
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
                    EventItem(
                        event = event,
                        onLongClick = {
                            viewModel.handleEvent(MainPageEvent.EventDelete(it))
                        },
                        onShortClick = {
                            viewModel.handleEvent(MainPageEvent.EventExpanded(it))
                        }
                    )
                }
            }
        }
    }
}