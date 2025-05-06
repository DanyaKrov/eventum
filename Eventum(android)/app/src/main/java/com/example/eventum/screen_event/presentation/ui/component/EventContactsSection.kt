package com.example.eventum.screen_event.presentation.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.domain.model.ContactsModel
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.SoftLightOrange

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventContactsSection(
    contacts: List<Contact>,
    onRemove: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    var contactToRemove by remember { mutableStateOf<Contact?>(null) }

    Column(modifier = modifier) {
        Text(
            text = "Прикреплённые контакты",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (contacts.isEmpty()) {
            Text(
                text = "Нет прикреплённых контактов",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            contacts.forEach { contact ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .combinedClickable(
                            onClick = {},
                            onLongClick = { contactToRemove = contact }
                        ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Box(Modifier.fillMaxWidth().background(
                        Brush.linearGradient(
                            colors = listOf(
                                SoftLightOrange,
                                Color.White
                            )
                        )
                    ))
                    {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = contact.name,
                                style = MaterialTheme.typography.titleSmall
                            )
                            if (contact.tag.isNotBlank()) {
                                Text(
                                    text = "Тэг: ${contact.tag}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }

                }
            }
        }
    }

    // Confirm remove dialogue
    if (contactToRemove != null) {
        AlertDialog(
            onDismissRequest = { contactToRemove = null },
            title = { Text("Открепить контакт?") },
            text = { Text("Вы уверены, что хотите открепить ${contactToRemove?.name}?") },
            confirmButton = {
                TextButton(onClick = {
                    contactToRemove?.let { onRemove(it) }
                    contactToRemove = null
                }) {
                    Text("Открепить")
                }
            },
            dismissButton = {
                TextButton(onClick = { contactToRemove = null }) {
                    Text("Отмена")
                }
            }
        )
    }
}