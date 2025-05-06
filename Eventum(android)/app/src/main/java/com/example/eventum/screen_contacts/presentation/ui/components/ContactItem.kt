package com.example.eventum.screen_contacts.presentation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_hello.presentation.ui.components.ButtonComponentType1
import com.example.eventum.screen_hello.presentation.ui.components.ButtonComponentType2
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.SoftLightOrange
import com.example.eventum.ui.theme.SoftLightRed
import com.example.eventum.ui.theme.SoftOrange
import com.example.eventum.ui.theme.SoftRed

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ContactItem(
    contact: Contact,
    onEdit: (Contact) -> Unit,
    onDelete: (Contact) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var editableName by remember { mutableStateOf(contact.name) }
    var editableLogin by remember { mutableStateOf("") }

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showChoice by remember { mutableStateOf(false) }

    var authColor by remember { mutableStateOf(SoftLightRed) }
    if (contact.authorisedStatus){
        authColor = Color(0xFF3FC958)
    }

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
                        showChoice = true
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
                text = contact.name,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
                color = Color.DarkGray
            )
        }
        Box(Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp))
        {
            Column(Modifier.fillMaxWidth()) {
                if (contact.authorisedStatus){
                    Text("Авторизованный",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color =authColor)
                    Spacer(Modifier.height(8.dp))
                    Text("Login",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray)
                    Text("Тут логин",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray,
                        fontSize = 17.sp)
                }
                else{
                    Text("Не авторизованный",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color =authColor)
                }
            }
        }
    }

//        if (isEditing) {
//            OutlinedTextField(
//                value = editableName,
//                onValueChange = { editableName = it },
//                label = { Text("Имя контакта") },
//                modifier = Modifier.fillMaxWidth()
//            )
//            Row(
//                horizontalArrangement = Arrangement.End,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                TextButton(onClick = {
//                    isEditing = false
//                    editableName = contact.name
//                }) {
//                    Text("Отмена")
//                }
//                TextButton(onClick = {
//                    if (editableName.isNotBlank()) {
//                        onEdit(contact.copy(name = editableName))
//                        isEditing = false
//                    }
//                }) {
//                    Text("Сохранить")
//                }
//            }
//        } else {
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Column {
//                    Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
//                    if (contact.tag.isNotBlank()) {
//                        Text(text = "Тэг: ${contact.tag}", style = MaterialTheme.typography.bodySmall)
//                    }
//                }
//                IconButton(onClick = { isEditing = true }) {
//                    Icon(Icons.Default.Edit, contentDescription = "Редактировать")
//                }
//            }
//        }
    if (showChoice)
    {
        AlertDialog(
            onDismissRequest = { showChoice = false },
            confirmButton = {
                TextButton(onClick = {
                    showChoice = false
                }) {
                    Text(
                        "Отмена",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color = SoftRed
                    )
                }
            },
            text = {
                Column(Modifier.fillMaxWidth()) {
                    ButtonComponentType2("Список подарков") {
                        TODO()
                    }
                    Spacer(Modifier.height(8.dp))
                    ButtonComponentType1("Удалить контакт") {
                        showChoice = false
                        showDeleteDialog = true
                    }
                }

            }
        )
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Удалить контакт?",
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray) },
            text = { Text("Вы уверены, что хотите удалить этот контакт?",
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray) },
            confirmButton = {
                TextButton(onClick = {
                    onDelete(contact)
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
    else if (isEditing)
    {
        AlertDialog(
            onDismissRequest = { isEditing = false },
            confirmButton = {
                TextButton(onClick = {
                    if (editableName.isNotBlank()) {
                        onEdit(contact.copy(name = editableName))
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
                        value = editableName,
                        onValueChange = { editableName = it },
                        label = {
                            Text(
                                "Имя контакта",
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
                    if (contact.authorisedStatus) {
                        OutlinedTextField(
                            value = editableLogin,
                            onValueChange = { editableLogin = it },
                            label = {
                                Text(
                                    "Login контакта",
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
                    "Редактирование контакта",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
            },
        )

    }
}