package com.example.eventum.screen_mainPage.presentation.ui.components

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.eventum.screen_mainPage.domain.model.Event
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.SoftLightOrange
import com.example.eventum.ui.theme.SoftOrange
import com.example.eventum.ui.theme.SoftRed

@Composable
fun EventItem(event: Event,
              onLongClick: (event: Event) -> Unit,
              onShortClick: (event: Event) -> Unit) {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Удалить событие?",
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray) },
            text = { Text("Вы уверены, что хотите удалить \"${event.name}\"?",
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray) },
            confirmButton = {
                TextButton(onClick = {
                    onLongClick(event)
                    showDialog = false
                }) {
                    Text("Удалить",
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium,
                        color = SoftRed)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
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
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        showDialog = true
                    },
                    onDoubleTap = {
                        onShortClick(event)
                    }
                )
            }
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
        ) {
//            Box(Modifier.fillMaxWidth().background(SoftRed))
//            {
//                event.picture?.let { imageUrl ->
//                    AsyncImage(
//                        model = imageUrl,
//                        contentDescription = event.name,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(180.dp)
//                    )
//                }
//            }
            Box(Modifier.fillMaxWidth().background(
                Brush.horizontalGradient(
                colors = listOf(
                    SoftOrange,
                    SoftLightOrange
                )))
                .padding(16.dp)
            )
            {
                Text(
                    text = event.name,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 25.sp
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = event.description,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = event.time,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            }
        }
    }
}