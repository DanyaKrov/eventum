package com.example.eventum.screen_event.presentation.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventum.screen_event.domain.model.NotificationModel
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.SoftLightOrange
import com.example.eventum.ui.theme.SoftLightRed
import com.example.eventum.ui.theme.SoftOrange
import com.example.eventum.ui.theme.SoftRed

@ExperimentalFoundationApi
@Composable
fun NotificationItem(
    notification: NotificationModel,
    onEdit: () -> Unit,
    onDelete: (notification: NotificationModel) -> Unit,
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Удалить напоминание?",
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray) },
            text = { Text("Вы уверены, что хотите удалить \"${notification.title}\"?",
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray) },
            confirmButton = {
                TextButton(onClick = {
                    onDelete(notification)
                    showDeleteDialog = false
                }) {
                    Text("Удалить",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color = SoftRed)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Отмена",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray)
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
//            .background(Color.DarkGray)
    ){
        Box(Modifier.fillMaxWidth().background(
            Brush.horizontalGradient(
                colors = listOf(
                    SoftOrange,
                    SoftLightOrange
                )
            )
        ).padding(16.dp)){
            Text(text = notification.title,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
                color = Color.DarkGray
            )
        }
        Box(Modifier.fillMaxWidth().background(Color.White)){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                onEdit()
                            },
                            onPress = {
                                showDeleteDialog = true
                            }
                        )
                    }
                    .padding(16.dp)
            ) {

//            Text(text = "Описание: ${notification.description}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Дата",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
//                fontSize = 17.sp,
                    color = Color.DarkGray)
                Text(text = notification.time,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp,
                    color = Color.DarkGray)
            }
        }


    }

}