package com.example.eventum.screen_giftList.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
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
import com.example.eventum.screen_giftList.domain.model.Gift
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.SoftLightOrange
import com.example.eventum.ui.theme.SoftOrange
import com.example.eventum.ui.theme.SoftRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiftItem(
    gift: Gift,
    onEdit: (gift: Gift)-> Unit,
    onDelete: (gift: Gift)-> Unit
             ){
    var editableTitle by remember { mutableStateOf(gift.presentTitle) }
    var editableDescription by remember { mutableStateOf(gift.presentDescription) }

    var isEditing by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .background(Color.White)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        isEditing = true
                    },
                    onLongPress = {
                        showDeleteDialog = true
                    }
                )
            },

        ){
        Box(
            Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            SoftOrange,
                            SoftLightOrange
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Text(
                text = gift.presentTitle,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 19.sp,
                color = Color.DarkGray
            )
        }
        Box(Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp))
        {
            Column(Modifier.fillMaxWidth()) {
                Text(
                    "Описание",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
                Text(
                    gift.presentDescription,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    fontSize = 17.sp
                )
            }
        }

    }
    if (isEditing){
        AlertDialog(
            onDismissRequest = { isEditing = false },
            confirmButton = {
                TextButton(onClick = {
                    if (editableTitle.isNotBlank()) {
                        onEdit(gift.copy(
                            presentTitle = editableTitle,
                            presentDescription = editableDescription
                        ))
                        isEditing = false
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
            text = {
                Column {
                    OutlinedTextField(
                        value = editableTitle,
                        onValueChange = { editableTitle = it },
                        label = {
                            Text(
                                "Название подарка",
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
                        value = editableDescription,
                        onValueChange = { editableDescription = it },
                        label = {
                            Text(
                                "Описание подарка",
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
            },
            dismissButton = {
                TextButton(onClick = {
                    isEditing = false
                }) {
                    Text(
                        "Отмена",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color = SoftRed
                    )
                }
            },
            title = {
                Text(
                    "Редактирование подарка",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
            },
        )
    }
    if (showDeleteDialog){
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Удалить подарок?",
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray) },
            text = { Text("Вы уверены, что хотите удалить ${gift.presentTitle} подарок?",
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray) },
            confirmButton = {
                TextButton(onClick = {
                    onDelete(gift)
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
}