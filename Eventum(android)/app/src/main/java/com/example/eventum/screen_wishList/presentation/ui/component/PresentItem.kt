package com.example.eventum.screen_wishList.presentation.ui.component

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.SoftLightOrange
import com.example.eventum.ui.theme.SoftOrange
import com.example.eventum.ui.theme.SoftRed
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresentItem(
    present: Present,
    onEdit: (Present) -> Unit,
    onDelete: (Present) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }

    var editableTitle by remember { mutableStateOf("") }
    var editableDescribtion by remember { mutableStateOf("") }

    LaunchedEffect(showEditDialog) {
        if (showEditDialog) {
            editableTitle = present.title
            editableDescribtion = present.description
        }
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        showEditDialog = true
                    },
                    onLongPress = {
                        showDeleteDialog = true
                    }
                )
            }

    ) {
        Box(
            Modifier.fillMaxWidth().background(
                Brush.horizontalGradient(
                    colors = listOf(
                        SoftOrange,
                        SoftLightOrange
                    )
                )
            ).padding(16.dp)
        ) {
            Text(
                text = present.title,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 21.sp,
                color = Color.DarkGray
            )
        }
        Box(Modifier.fillMaxWidth().background(Color.White)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(16.dp)
            ) {

//            Text(text = "Описание: ${notification.description}", style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = "Описание",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
//                fontSize = 17.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = present.description,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp,
                    color = Color.DarkGray
                )
            }
        }


    }
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(
                    "Удалить подарок?",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
            },
            text = {
                Text(
                    "Вы уверены, что хотите удалить \"${present.title}\"?",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    onDelete(present)
                    showDeleteDialog = false
                }) {
                    Text(
                        "Удалить",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color = SoftRed
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(
                        "Отмена",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                }
            }
        )
    }
    if (showEditDialog) {
        AlertDialog(
            confirmButton = {
                TextButton(onClick = {
                    if (editableTitle.isNotBlank() && editableDescribtion.isNotBlank()) {
                        onEdit(present.copy(
                            title = editableTitle,
                            description = editableDescribtion
                        ))
                        showEditDialog = false
                    }
                }) {
                    Text(
                        "Сохранить",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showEditDialog = false
                }) {
                    Text(
                        "Отмена",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color = SoftRed
                    )
                }
            },

            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(
                    "Редактирование подарка",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = editableTitle,
                        onValueChange = { editableTitle = it },
                        label = {
                            Text(
                                "Название",
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Medium
                            )
                        },
                        shape = RoundedCornerShape(40.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.LightGray,
                            focusedLabelColor = Color.Gray,
                            unfocusedLabelColor = Color.LightGray
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = editableDescribtion,
                        onValueChange = { editableDescribtion = it },
                        label = {
                            Text(
                                "Описание",
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Medium
                            )
                        },
                        shape = RoundedCornerShape(40.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.LightGray,
                            focusedLabelColor = Color.Gray,
                            unfocusedLabelColor = Color.LightGray
                        )
                    )


                }
            }
        )
    }

//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(
//                    text = present.title,
//                    style = MaterialTheme.typography.titleMedium
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = present.description,
//                    style = MaterialTheme.typography.bodyMedium
//                )
//            }
//
//            Box {
//                IconButton(onClick = { showMenu = true }) {
//                    Icon(Icons.Default.MoreVert, contentDescription = "Меню подарка")
//                }
//
//                DropdownMenu(
//                    expanded = showMenu,
//                    onDismissRequest = { showMenu = false }
//                ) {
//                    DropdownMenuItem(
//                        text = { Text("Редактировать") },
//                        onClick = {
//                            showMenu = false
//                            onEdit(present)
//                        }
//                    )
//                    DropdownMenuItem(
//                        text = { Text("Удалить") },
//                        onClick = {
//                            showMenu = false
//                            onDelete(present)
//                        }
//                    )
//                }
//            }
//        }
//        Divider()
    }
//}