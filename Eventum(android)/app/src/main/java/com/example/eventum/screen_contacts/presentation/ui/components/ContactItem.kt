package com.example.eventum.screen_contacts.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eventum.screen_contacts.domain.model.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactItem(
    contact: Contact,
    onEdit: (Contact) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var editableName by remember { mutableStateOf(contact.name) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {

        if (isEditing) {
            OutlinedTextField(
                value = editableName,
                onValueChange = { editableName = it },
                label = { Text("Имя контакта") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = {
                    isEditing = false
                    editableName = contact.name
                }) {
                    Text("Отмена")
                }
                TextButton(onClick = {
                    if (editableName.isNotBlank()) {
                        onEdit(contact.copy(name = editableName))
                        isEditing = false
                    }
                }) {
                    Text("Сохранить")
                }
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
                    if (contact.tag.isNotBlank()) {
                        Text(text = "Тэг: ${contact.tag}", style = MaterialTheme.typography.bodySmall)
                    }
                }
                IconButton(onClick = { isEditing = true }) {
                    Icon(Icons.Default.Edit, contentDescription = "Редактировать")
                }
            }
        }
    }
}