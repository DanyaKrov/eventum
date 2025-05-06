package com.example.eventum.screen_profile.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventum.common.Constants
import com.example.eventum.screen_event.presentation.event.EventPageEvent
import com.example.eventum.screen_hello.presentation.ui.components.ButtonComponentType1
import com.example.eventum.screen_hello.presentation.ui.components.ButtonComponentType2
import com.example.eventum.screen_mainPage.presentation.ui.components.ScreenNavigator
import com.example.eventum.screen_profile.presentation.event.ProfileEvent
import com.example.eventum.screen_profile.presentation.event.ProfileNavigationEvent
import com.example.eventum.screen_profile.presentation.ui.component.ProfileCard
import com.example.eventum.screen_profile.presentation.viewModel.ProfileViewModel
import com.example.eventum.ui.theme.Montserrat
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val model = viewModel.model.value
    val snackbarHostState = remember { SnackbarHostState() }
    var isEditing by remember { mutableStateOf(false) }
    val rounding = 40.dp

    var editableName by remember { mutableStateOf(viewModel.model.value.user?.name?:"") }
    var editableEmail by remember { mutableStateOf(viewModel.model.value.user?.email?:"") }

    LaunchedEffect(isEditing) {
        if (isEditing) {
            model.user?.let {
                editableName = it.name
                editableEmail = it.email
            }
        }
    }

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
                        viewModel.handleNavigationEvent(ProfileNavigationEvent.NavigateToContactsPage())
                    },
                    onNavigateToProfileScreen = {
//                        viewModel.handleNavigationEvent(ProfileNavigationEvent.Na)
                    },
                    onNavigateToWishListScreen = {
                        viewModel.handleNavigationEvent(ProfileNavigationEvent.NavigateToWishListPage())
                    },
                    onNavigateToMainScreen = {
                        viewModel.handleNavigationEvent(ProfileNavigationEvent.NavigateToMainPage())
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
                    title = { androidx.compose.material3.Text("Профиль") },
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
            if (model.uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding), contentAlignment = Alignment.Center
                ) {}
            } else {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                        .fillMaxSize()
                )
                {
                    model.user?.let{ user->
                        if (isEditing) {
                            Text(
                                "Редактирование профиля", fontSize = 22.sp,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Medium,
                                color = Color.DarkGray
                            )
                            Spacer(Modifier.height(8.dp))

                            OutlinedTextField(
                                value = editableName,
                                onValueChange = { editableName = it },
                                label = { Text("Имя пользователя") },
                                shape = RoundedCornerShape(rounding),
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(Modifier.height(8.dp))
                            OutlinedTextField(
                                value = editableEmail,
                                onValueChange = { editableEmail = it },
                                label = { Text("E-mail") },
                                shape = RoundedCornerShape(rounding),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(16.dp))
                            ButtonComponentType1("Сохранить") {
                                viewModel.handleEvent(
                                    ProfileEvent.UpdateUser(
                                        user.copy(
                                            name = editableName,
                                            email = editableEmail
                                        )
                                    )
                                )
                                isEditing = false
                            }
                        } else {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(padding)
                                    .padding(16.dp)
                            ) {
                                ProfileCard(user)
                                Spacer(Modifier.height(17.dp))
                                ButtonComponentType2("Редактировать")
                                {
                                    isEditing = true
                                }
                                Spacer(Modifier.height(20.dp))
                                ButtonComponentType1("Выйти из аккаунта") {
                                    viewModel.handleNavigationEvent(ProfileNavigationEvent.ExitFromAccount())
                                }

                            }
                        }
                    }

                }

            }
        }
    }

}