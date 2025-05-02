package com.example.eventum.screen_event.presentation.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eventum.screen_event.domain.model.NotificationModel

@ExperimentalFoundationApi
@Composable
fun NotificationItem(
    notification: NotificationModel,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onEdit,
                onLongClick = onDelete
            )
            .padding(8.dp)
    ) {
        Text(text = "Название: ${notification.title}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Описание: ${notification.description}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Дата: ${notification.time}", style = MaterialTheme.typography.bodySmall)
    }
}