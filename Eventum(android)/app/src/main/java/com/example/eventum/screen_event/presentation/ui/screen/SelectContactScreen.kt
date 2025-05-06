package com.example.eventum.screen_event.presentation.ui.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.eventum.screen_contacts.domain.model.Contact
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventum.screen_contacts.domain.model.ContactsModel
import com.example.eventum.screen_event.presentation.ui.component.ContactSelectItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectContactScreen(
    allContacts: List<Contact>,
    attachedContacts: List<Contact>,
    onAttachContacts: (List<Contact>) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDropdown by remember { mutableStateOf(false) }
    var selectedIds by remember { mutableStateOf(setOf<Long>()) }

    val availableContacts = allContacts.filterNot { existing ->
        existing.remoteId in attachedContacts.map { it.remoteId }
    }

    Column(modifier = modifier) {
        Button(
            onClick = {
                showDropdown = !showDropdown
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Добавить контакты")
        }

        AnimatedVisibility(visible = showDropdown) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF8F8F8), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Text("Выберите контакты:")
                Spacer(modifier = Modifier.height(8.dp))

                availableContacts.forEach { contact ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = selectedIds.contains(contact.remoteId),
                            onCheckedChange = { checked ->
                                selectedIds = if (checked)
                                    selectedIds + contact.remoteId
                                else
                                    selectedIds - contact.remoteId
                            }
                        )
                        Text(contact.name, fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        val selectedContacts = availableContacts.filter {
                            it.remoteId in selectedIds
                        }
                        onAttachContacts(selectedContacts)
                        selectedIds = emptySet()
                        showDropdown = false
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Прикрепить")
                }
            }
        }
    }
}