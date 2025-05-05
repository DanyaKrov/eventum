package com.example.eventum.screen_mainPage.presentation.ui.components

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventum.domain.model.DomainState
import com.example.eventum.screen_mainPage.domain.model.EventRequestModel
import com.example.eventum.screen_mainPage.presentation.viewModel.MainPageViewModel
import com.example.eventum.ui.theme.BackGround
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.SoftLightOrange
import com.example.eventum.ui.theme.SoftLightRed
import com.example.eventum.ui.theme.SoftOrange
import com.example.eventum.ui.theme.SoftRed
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEvent (
    context: Context,
    onCreate: (EventRequestModel) -> Unit,
    creationStatus: State<DomainState>,
    snackbarHostState: SnackbarHostState
) {

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    val calendar = remember { Calendar.getInstance() }
    val padding = 16.dp
    val rounding = 40.dp


    LaunchedEffect(creationStatus.value)  {
        creationStatus.value.isSuccess?.let {
            if (it) {
                snackbarHostState.showSnackbar("Событие создано успешно")
                name = ""
                description = ""
            }
        }
    }

    fun openDateTimePicker() {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                selectedDate = format.format(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .background(Color.White)
    ){
        Box(Modifier.fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        SoftRed,
                        SoftLightRed
                    )
                ))
            .padding(padding)
        )
        {
            Text(
                text = "Создать событие",
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 25.sp,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(padding)
    ) {

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Название события",
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(rounding),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray,
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.LightGray
            ),
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Описание",
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(rounding),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray,
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.LightGray
            ),

        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            label = { Text("Даты события",
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium) },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {
                    openDateTimePicker()
                }) {
                    Icon(Icons.Default.CalendarToday, contentDescription = "Выбрать дату")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(rounding),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray,
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.LightGray
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isNotBlank() && selectedDate.isNotBlank()) {
                    val newEvent = EventRequestModel(
                        name = name,
                        description = description,
                        time = selectedDate,
                    )
                    onCreate(newEvent)
                    name = ""
                    description = ""
                } else {
                    // show text on how it is incorrect
                }
            },
            modifier = Modifier.fillMaxWidth()
                .background(Brush.horizontalGradient(
                    colors = listOf(
                        SoftRed,
                        SoftLightRed
                    )),
                    shape = MaterialTheme.shapes.extraLarge),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)

        ) {
            Text("Создать событие",
                color = Color.White)
        }
    }
    }
}