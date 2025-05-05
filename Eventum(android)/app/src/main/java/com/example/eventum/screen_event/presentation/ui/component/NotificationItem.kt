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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    onDelete: (notification: NotificationModel) -> Unit
) {
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
                            onDoubleTap = {
                                onDelete(notification)
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