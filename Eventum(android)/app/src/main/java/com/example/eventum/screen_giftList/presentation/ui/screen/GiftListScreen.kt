package com.example.eventum.screen_giftList.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
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
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.screen_giftList.domain.model.GiftRequestModel
import com.example.eventum.screen_giftList.domain.model.GiftState
import com.example.eventum.screen_giftList.presentation.event.GiftListEvent
import com.example.eventum.screen_giftList.presentation.event.GiftListNavigationEvent
import com.example.eventum.screen_giftList.presentation.ui.component.GiftItem
import com.example.eventum.screen_giftList.presentation.viewModel.GiftListViewModel
import com.example.eventum.screen_hello.presentation.ui.components.ButtonComponentType2
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.SoftRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiftListScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: GiftListViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val model = viewModel.model

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
    var newTitle by remember { mutableStateOf("") }
    var newDescription by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.handleNavigationEvent(GiftListNavigationEvent.MoveBack())
                },
                containerColor = Color.White
            ) {
                Icon(Icons.Sharp.ArrowBack, contentDescription = "Назад")
            }
        }
    ) { padding ->
        if (model.value.uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding), contentAlignment = Alignment.Center
            ) {}
        } else {
            Column(Modifier.fillMaxWidth().padding(16.dp)){
                Text(
                    "Список подарков",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Montserrat
                )
                Spacer(Modifier.height(8.dp))
                if (model.value.gifts?.isEmpty() ?: true) {
                    Text("Список подарков пуст", style = MaterialTheme.typography.bodyMedium)
                }
                else {
                    LazyColumn() {
                        items(model.value.gifts!!) { gift ->
                            GiftItem(
                                gift = gift,
                                onEdit = { viewModel.handleEvent(GiftListEvent.EditGiftEvent(gift)) },
                                onDelete = {
                                    viewModel.handleEvent(
                                        GiftListEvent.DeleteGiftEvent(gift)
                                    )
                                }
                            )
                            Spacer(Modifier.height(8.dp))
                        }
                        //Для теста отображения на экране
                        item {
                            GiftItem(
                                gift = Gift(
                                    0,
                                    0,
                                    "Тестовое название",
                                    "Тестовое описание",
                                    5,
                                    GiftState(0, 0, ""),
                                    5
                                ),
                                onEdit = {},
                                onDelete = {}
                            )
                        }
                    }
                }
                GiftItem(
                    gift = Gift(
                        0,
                        0,
                        "Тестовое название",
                        "Тестовое описание",
                        5,
                        GiftState(0, 0, ""),
                        5
                    ),
                    onEdit = {},
                    onDelete = {}
                )
                    Spacer(Modifier.height(8.dp))


                Spacer(Modifier.height(8.dp))
                ButtonComponentType2("Добавить подарок") {
                    showAddDialog = true
                }
            }
            if (showAddDialog){
                AlertDialog(
                    onDismissRequest = { showAddDialog = false },
                    title = { Text("Создать подарок",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray) },
                    text = {
                        Column(Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = newTitle,
                                onValueChange = { newTitle = it },
                                label = { Text("Название подарка") },
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
                                value = newDescription,
                                onValueChange = { newDescription = it },
                                label = { Text("Описание (не обязательно)") },
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
                            viewModel.handleEvent(
                                GiftListEvent.AddGiftEvent(
                                    GiftRequestModel(
                                        newTitle,
                                        newDescription
                                    )
                                )
                            )
                                newTitle = ""
                                showAddDialog = false
                                newTitle = ""
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
                            newTitle = ""
                            newTitle = ""
                        }) {
                            Text("Отмена",
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Medium,
                                color = SoftRed
                            )
                        }
                    }
                )
            }
        }
    }

}