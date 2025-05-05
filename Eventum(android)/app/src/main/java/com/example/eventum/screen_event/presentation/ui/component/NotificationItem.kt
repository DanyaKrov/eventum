package com.example.eventum.screen_event.presentation.ui.component

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.screen_event.presentation.event.EventPageEvent
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.SoftLightOrange
import com.example.eventum.ui.theme.SoftLightRed
import com.example.eventum.ui.theme.SoftOrange
import com.example.eventum.ui.theme.SoftRed
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun NotificationItem(
    notification: NotificationModel,
    onEdit: (notification: NotificationModel) -> Unit,
    onDelete: (notification: NotificationModel) -> Unit,
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    var editableTitle by remember { mutableStateOf("") }
    var editableDate by remember { mutableStateOf("") }
    LaunchedEffect(showEditDialog) {
        if (showEditDialog) {
                editableTitle = notification.title
                editableDate = notification.time
            }
        }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(
                    "Удалить напоминание?",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
            },
            text = {
                Text(
                    "Вы уверены, что хотите удалить \"${notification.title}\"?",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    onDelete(notification)
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
                    if (editableTitle.isNotBlank() && editableDate.isNotBlank()) {
                        onEdit(notification.copy(
                            title = editableTitle,
                            time = editableDate
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
                    "Редактирование напоминания",
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
                                "Заголовок",
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
                        value = editableDate,
                        onValueChange = { editableDate = it },
                        label = {
                            Text(
                                "Дата (yyyy-MM-dd)",
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Medium,
                            )
                        },
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = {
                                DatePickerDialog(
                                    context,
                                    { _, year, month, dayOfMonth ->
                                        calendar.set(year, month, dayOfMonth)
                                        val format =
                                            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
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
                }
            }
        )
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
                    text = notification.title,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp,
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
                        text = "Дата",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
//                fontSize = 17.sp,
                        color = Color.DarkGray
                    )
                    Text(
                        text = notification.time,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp,
                        color = Color.DarkGray
                    )
                }
            }


        }

    }
